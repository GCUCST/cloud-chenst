package cn.cst.controller.api;

import cn.cst.exception.LoginException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Cacheable(cacheNames = {"testData1"})
public class ApiController {


    @GetMapping("/test/data")
    public String testGet( ) {
       if(true) throw new  LoginException("异常？？、","404");
        return "data...";
    }
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
