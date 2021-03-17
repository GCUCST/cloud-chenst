package cn.cst.controller;


import cn.cst.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        session.setAttribute("username","陈少荣");
        Map<String,String> map = new  HashMap<String,String>();
        map.put("msg","你好...");
        model.addAllAttributes(map);
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute User user) {
        System.out.println(user.getName());
        return "index";
    }
}
