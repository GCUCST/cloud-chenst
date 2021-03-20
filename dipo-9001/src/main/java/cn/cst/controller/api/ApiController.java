package cn.cst.controller.api;

import cn.cst.entity.User;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class ApiController {


    @PostMapping("/api/login")
    public String add(@ModelAttribute User user, HttpSession session) {
        session.setAttribute("username", user.getName());
        return "index";
    }

    @GetMapping("/api/rest")
    public String rest() {
        return "index";
    }

}
