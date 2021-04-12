package cn.cst.controller.api;

import cn.cst.exception.CustomException;
import cn.cst.service.WebSocketServer;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class WebSocketController {
    @RequestMapping("/checkRoom/{roomId}")
    public ResponseEntity<String> check(HttpSession session, @PathVariable("roomId") String roomId) throws IOException {

        //模拟
        if(false)throw new CustomException(400,"房间不存在");
        if(false)throw new CustomException(400,"房间已满");
        if(null==session.getAttribute("user"))
            throw new CustomException(400,"该操作需要登录");

        return ResponseEntity.ok("正在进入房间");
    }

//    @RequestMapping("/push/{roomId}")
//    public ResponseEntity<String> pushToWeb(String message, @PathVariable String roomId) throws IOException {
//        WebSocketServer.sendInfo(message, roomId);
//        return ResponseEntity.ok("MSG SEND SUCCESS");
//    }
}
