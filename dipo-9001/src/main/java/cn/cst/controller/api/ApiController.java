package cn.cst.controller.api;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Cacheable
public class ApiController {


//    @PostMapping("/api/login")
//    public String add(@ModelAttribute User user, HttpSession session) {
//        session.setAttribute("username", user.getName());
//        return "index";
//    }
//
//    @GetMapping("error")
//    public String rest() {
//        return "index";
//    }



    @GetMapping("testRedis")
    @Cacheable("ChenST")
    public String rest() {
        System.out.println("testRedis is called...");
        return "index";
    }

}
