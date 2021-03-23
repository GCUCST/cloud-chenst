package cn.cst.controller.api;

import cn.cst.dao.UserRepository;
import cn.cst.entity.User;
import cn.cst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

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

}
