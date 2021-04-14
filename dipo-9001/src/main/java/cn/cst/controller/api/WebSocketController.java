package cn.cst.controller.api;

import cn.cst.entity.Room;
import cn.cst.entity.User;
import cn.cst.exception.CustomException;
import cn.cst.utils.RoomsManagement;
import cn.cst.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/room")
@Slf4j
public class WebSocketController {
    @RequestMapping("/check/{roomId}")
    public ResponseEntity<String> checkRoom(HttpSession session, @PathVariable("roomId") String roomId) {
        //模拟
        Room targetRoom = RoomsManagement.getRoomById(roomId);
        if (targetRoom==null) throw new CustomException(400, "房间不存在");
//        if (false) throw new CustomException(400, "房间已满");
        if (null == SessionUtil.getUser(session))
            throw new CustomException(400, "该操作需要登录");
        return ResponseEntity.ok("正在进入房间"+roomId);
    }

    @GetMapping
    public ResponseEntity<Room> getRoomById(String roomId) {
        Room room = RoomsManagement.getRoomById(roomId);
        log.info("获取房间：{}",room);
        return ResponseEntity.ok(room);
    }
    @GetMapping("/list")
    public ResponseEntity<List> listRooms() {
        List<Room> rooms = RoomsManagement.listRooms();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(HttpSession session, @RequestBody @Valid Room room) {
        //模拟
        final String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
        User user = SessionUtil.getUser(session);
        room.setId(id);
        room.setRoomIdNum(id);
        room.setCreatedTime(LocalDateTime.now());
        room.setUserId(user.getId());
        room.setUsername(user.getUsername());
        room.setRoomSpace(500);
        room.setOnLinePlayerNum(0);
        room.setRoomCoverUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201505%2F04%2F20150504003238_NykiS.thumb.700_0.jpeg&refer=http%3A%2F%2Fimg3.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620728378&t=9667cd7cf1e9989a8ab8bfce29a8ea50");
        room.setStatus("正常");
        Room resultRoom = RoomsManagement.createRoom(room);
        return ResponseEntity.ok(resultRoom);
    }

//    @RequestMapping("/push/{roomId}")
//    public ResponseEntity<String> pushToWeb(String message, @PathVariable String roomId) throws IOException {
//        WebSocketServer.sendInfo(message, roomId);
//        return ResponseEntity.ok("MSG SEND SUCCESS");
//    }
}
