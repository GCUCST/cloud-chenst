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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("msg","注册页面");
        return REGISTER_PAGE;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg","登录页面");
        return INDEX_PAGE;
    }

    @GetMapping("/login")
    public String add() {
        return LOGIN_PAGE;
    }

    @GetMapping("/user/info")
    public String userInfo(Model model,HttpSession session) {
        if(session.getAttribute("user")==null){
            model.addAttribute("msg","需要登录");
            return INDEX_PAGE;
        }
        model.addAttribute("msg","用户资料");
        return USER_INFO_PAGE;
    }

    @GetMapping("/article")
    public String article(Model model,HttpSession session) {
        return ARTICLE_PAGE;
    }








}
