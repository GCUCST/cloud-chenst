package cn.cst.service;

import cn.cst.entity.SocketUser;
import cn.cst.entity.User;
import cn.cst.exception.CustomException;
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
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/chatServer/{socketUserJson}")
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

    private SocketUser socketUser;

    private String key = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("socketUserJson") String socketUserJson) {
        System.out.println("{" + socketUserJson + "}");
        JSONObject jsonObject = JSONObject.parseObject("{" + socketUserJson + "}");
        String userId = jsonObject.getString("userId");
        String roomId = jsonObject.getString("roomId");
        this.session = session;
        key = roomId + "/" + userId;

        this.userId = userId;
        socketUser = SocketUser.builder().userId(userId).roomId(roomId).build();


        if (webSocketMap.containsKey(key)) {
            webSocketMap.remove(key);
            webSocketMap.put(key, this);
            //加入set中
        } else {
            webSocketMap.put(key, this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:" + this.key + ",当前在线人数为:" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:" + this.key + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(key)) {
            webSocketMap.remove(key);
            //从set中删除
            subOnlineCount();
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
        log.info("用户消息:" + key + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis

        Enumeration<String> keys = webSocketMap.keys();

        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                //传送给对应toUserId用户的websocket
                keys.asIterator().forEachRemaining(con -> {
                    if (con.startsWith(this.socketUser.getRoomId())) {
                        try {
                            webSocketMap.get(con).sendMessage(jsonObject.toJSONString());
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
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
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

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
