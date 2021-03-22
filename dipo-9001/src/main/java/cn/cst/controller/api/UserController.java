package cn.cst.controller.api;

import cn.cst.dao.UserRepository;
import cn.cst.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "addUser")
    public void addPerson(User person) {
        userRepository.save(person);
    }

    @DeleteMapping(path = "deleteUser")
    public void deletePerson(String id) {
        userRepository.deleteById(id);
    }

}
