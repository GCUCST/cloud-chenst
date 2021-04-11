package cn.cst.controller.api;

import cn.cst.dao.UserRepository;
import cn.cst.entity.User;
import cn.cst.entity.UserInfo;
import cn.cst.exception.CustomException;
import cn.cst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "api/user")
public class UserController {



    @Autowired
    private UserService userService;

    @PutMapping(path = "")
    public ResponseEntity<User> updateUserInfo(HttpSession session, @RequestParam(value = "username",required = true) String username,
                                               @RequestParam(value = "wechat",required = true)  String wechat,
                                               @RequestParam(value = "personalWebSite",required = true)  String personalWebSite,
                                               @RequestParam(value = "introduction",required = true)  String introduction) {
        User userSave = userService.updateUserInfo(username,wechat,personalWebSite,introduction);
        session.setAttribute("user",userSave);
        session.setAttribute("username",userSave.getUsername());
        return ResponseEntity.ok(userSave);
    }

    @PostMapping(path = "")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userSave = userService.save(user);
        return ResponseEntity.ok(userSave);
    }

    @DeleteMapping(path = "{id}")
    public  ResponseEntity deleteUser(@PathVariable("id") String id) {
        userService.deleteById(id);
        return  ResponseEntity.ok().body(1);
    }


    @GetMapping(path = "info")
    public  ResponseEntity getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            throw new CustomException(401,"请先登录...");
        }
        return  ResponseEntity.ok().body(user);
    }

}
