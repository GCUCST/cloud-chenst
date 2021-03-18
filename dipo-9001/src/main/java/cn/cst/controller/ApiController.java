package cn.cst.controller;

import cn.cst.entity.User;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class ApiController {

    @PostMapping("/api/login")
    public String add(@ModelAttribute User user, HttpSession session) {
        session.setAttribute("username", user.getName());
        return "index";
    }

    @PostMapping("/api/rest")
    @ResponseBody
    public String rest() {

        return "index";
    }

}
