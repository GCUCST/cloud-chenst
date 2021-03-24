package cn.cst.controller.api;

import cn.cst.entity.User;
import cn.cst.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody @Valid User user, HttpSession session){
        log.info(user.getUsername());
        User loginUser = userService.loginUser(user);
        session.setAttribute("user",loginUser);
        session.setAttribute("username",loginUser.getUsername());
        System.out.println(loginUser.getId());
        return ResponseEntity.ok(loginUser);

    }
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpSession session){
        session.removeAttribute("username");
        return ResponseEntity.ok(true);
    }

}
