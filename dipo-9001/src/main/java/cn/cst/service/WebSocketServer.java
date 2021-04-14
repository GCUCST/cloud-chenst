package cn.cst.service;

import cn.cst.config.ServerEncoder;
import cn.cst.entity.MessageTemplate;
import cn.cst.entity.Room;
import cn.cst.entity.SocketUser;
import cn.cst.entity.User;
import cn.cst.exception.CustomException;
import cn.cst.utils.JsonUtil;
import cn.cst.utils.MessageUtil;
import cn.cst.utils.RoomsManagement;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value="/chatServer/{socketUserJson}", encoders = { ServerEncoder.class })
@Component
public class WebSocketServer {

    static Log log = LogFactory.get(WebSocketServer.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";
    private String roomId = "";

    private SocketUser socketUser;

    private String key = "";

    private void buildProcess(SocketUser socketUser,Session session)
    {
        this.session = session;
        this.userId = socketUser.getUserId();
        this.roomId = socketUser.getRoomId();
        this.socketUser = socketUser;
        this.key = socketUser.getRoomId() + "/" + socketUser.getUserId();
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("socketUserJson") String socketUserJson) {
        String targetStr = "{" + socketUserJson + "}";
        SocketUser socketUser = JsonUtil.getSocketUser(targetStr);
        this.buildProcess(socketUser,session);

        // 判断当前room存在否
        Boolean exist = RoomsManagement.existRoomById(this.roomId);
        if(!exist)throw new CustomException(404,"当前房间未找到");


        if (webSocketMap.containsKey(key)) {
            webSocketMap.remove(key);
            webSocketMap.put(key, this);
            //加入set中
        } else {
            webSocketMap.put(key, this);
            //加入set中
            addOnlineCount(roomId);
            //在线数加1
        }

        log.info("用户连接:" + this.key + ",当前在线总人数为:" + getOnlineCount());
        try {
            sendMessage(MessageUtil.systemMessage(this.userId+"建立连接成功"));
            for (MessageTemplate msg: getHistoryMsgs(roomId)){
                sendMessage(msg);
            }
            sendMsgInRoom(this.userId+"来了");
        } catch (IOException | EncodeException e) {
            log.error("用户:" + this.key + ",网络异常!!!!!!");
        }
    }

    public static Map<String,ArrayList<MessageTemplate>> historyMsgs = new HashMap<>();
    private synchronized void saveToHistory(String roomId,MessageTemplate template){
        if(historyMsgs!=null&&historyMsgs.containsKey(roomId)){
            ArrayList<MessageTemplate> messageTemplates = historyMsgs.get(roomId);
            if(messageTemplates.size()>20){
                messageTemplates.remove(0);
            }
            messageTemplates.add(template);
        }
        else {
            historyMsgs = new HashMap<>();
            ArrayList<MessageTemplate> messageTemplates = new ArrayList<>();
            historyMsgs.put(roomId,messageTemplates);
        }
    }

    private List<MessageTemplate> getHistoryMsgs(String roomId){
        if(historyMsgs==null)return new ArrayList<>();
        if(historyMsgs.get(roomId)==null)return new ArrayList<>();
        return historyMsgs.get(roomId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(key)) {
            webSocketMap.remove(key);
            //从set中删除
            subOnlineCount(roomId);
        }
        log.info("用户退出:" + key + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        sendMsgInRoom(message);
    }

    private void sendMsgInRoom(String message){
        log.info("用户消息:" + key + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        Enumeration<String> keys = webSocketMap.keys();
        if (StringUtils.isNotBlank(message)) {
            try {
                keys.asIterator().forEachRemaining(con -> {
                    if (con.startsWith(this.socketUser.getRoomId())) {
                        try {
                            try {
                                webSocketMap.get(con).sendMessage(MessageUtil.buildMessage(message,socketUser));
                                saveToHistory(this.socketUser.getRoomId(),MessageUtil.buildMessage(message,socketUser));
                            } catch (EncodeException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            log.error("请求的roomId/userId:" + "不在该服务器上");
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(MessageTemplate message) throws IOException, EncodeException {
//        this.session.getBasicRemote().sendObject(message);
        this.session.getBasicRemote().sendText(JsonUtil.getMsgJsonStr(message));
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("roomId") String roomId) throws IOException {


        log.info("发送消息到:" + roomId + "，报文:" + message);

        Enumeration<String> keys = webSocketMap.keys();
        keys.asIterator().forEachRemaining(con -> {
            System.out.println(con);
        });


    }

    public  synchronized int getOnlineCount() {
        return onlineCount;
    }

    public  synchronized void addOnlineCount(String roomId) {
        WebSocketServer.onlineCount++;
        Boolean existRoom = RoomsManagement.existRoomById(roomId);
        if(!existRoom)return;
        Room room = RoomsManagement.getRoomById(roomId);
        room.getUsers().add(socketUser);
        room.setOnLinePlayerNum(room.getOnLinePlayerNum()+1);
    }

    public  synchronized void subOnlineCount(String roomId) {
        WebSocketServer.onlineCount--;
        Boolean existRoom = RoomsManagement.existRoomById(roomId);
        if(!existRoom)return;
        Room room = RoomsManagement.getRoomById(roomId);
        room.getUsers().remove(socketUser);
        room.setOnLinePlayerNum(room.getOnLinePlayerNum()-1);
    }
}
