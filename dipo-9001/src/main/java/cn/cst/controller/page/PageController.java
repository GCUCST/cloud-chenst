package cn.cst.controller.page;


import cn.cst.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class PageController extends AbstractController {

    @GetMapping("/")
    public String defaultPage(HttpSession session, HttpServletRequest request) {

        return INDEX_PAGE;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg","我的消息..");
        return INDEX_PAGE;
    }

    @GetMapping("/login")
    public String add() {
        return LOGIN_PAGE;
    }
}
