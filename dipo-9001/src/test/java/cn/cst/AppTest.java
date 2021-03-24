package cn.cst;

import cn.cst.controller.api.LoginController;
import cn.cst.controller.api.UserController;
import cn.cst.dao.AddressRepository;
import cn.cst.dao.DepartmentRepository;
import cn.cst.dao.EmpolyeeRepository;
import cn.cst.dao.UserRepository;
import cn.cst.entity.Address;
import cn.cst.entity.Department;
import cn.cst.entity.Empolyee;
import cn.cst.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest

public class AppTest {


    //@RestController
    //@RequestMapping(value = "api/user")
    //public class UserController {
    //
    //    @Autowired
    //    private UserService userService;
    //
    //    @PostMapping(path = "")
    //    public ResponseEntity<User> addUser(@RequestBody User user) {
    //        User userSave = userService.save(user);
    //        return ResponseEntity.ok(userSave);
    //    }
    @Autowired
    UserController userController;

    @Autowired
    LoginController loginController;

    @Test
    public void testReg() {
        User user = User.builder().username("cst").password("123").build();
        userController.addUser(user);
    }

    @Test
    public void testLogin() {
        User user = User.builder().username("cst").password("123").build();
        loginController.loginUser(user, null);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

//    @Test
//    public void testManyToOne() {
//        User user = User.builder().username("CST").password("123").build();
//        Address address = Address.builder().detail("AAA").user(user).build();
//        Address address2 = Address.builder().detail("BBB").user(user).build();
//        userRepository.save(user);
//        addressRepository.save(address);
//        addressRepository.save(address2);
//
//    }
//    @Test
//    public void testManyToOneFind() {
//        List<Address> all = addressRepository.findAll();
//        for (Address address:all){
//            System.out.println(address.getUser().getUsername());
//        }
//    }

//    @Test
//    public void testOneToMany() {
//        Address address = Address.builder().detail("AAA").build();
//        Address address2 = Address.builder().detail("BBB").build();
//        User user = User.builder().username("CST").password("123").addresses(new HashSet<>()).build();
//        System.out.println(user.getAddresses().isEmpty());
//        user.getAddresses().add(address);
//        user.getAddresses().add(address2);
//        addressRepository.save(address);
//        addressRepository.save(address2);
//        userRepository.save(user);
//    }
//    @Test
//    public void testOneToManyFind() {
//        List<User> all = userRepository.findAll();
//        for (User u:all){
//            System.out.println(u.getAddresses());
//        }
//    }

    @Test
    public void testOneToManyToOne() {
        Address address = Address.builder().detail("AAA").build();
        Address address2 = Address.builder().detail("BBB").build();
        User user = User.builder().username("CST").password("123").addresses(new HashSet<>()).build();
        user.getAddresses().add(address);
        user.getAddresses().add(address2);
        address.setUser(user);
        address2.setUser(user);
        userRepository.save(user);
    }
    @Test
    public void testOneToManyToOneFind() {
        List<User> all = userRepository.findAll();
        for (User u:all){
            System.out.println(u.getAddresses());
        }
    }
    @Test
    public void testOneToManyToOneFindA() {
        List<Address> all = addressRepository.findAll();
        for (Address u:all){
            System.out.println(u.getUser());
        }
    }

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmpolyeeRepository empolyeeRepository;

//    @Test
//    public void testMTO() {
//        Empolyee empolyee = new Empolyee();
//        empolyee.setName("老王");
//        empolyee.setJob("测试");
//
//        Department department = new Department();
//        department.setName("产品二部");
//
//        empolyee.setDepartment(department);
//
////        departmentRepository.save(department);
//        empolyeeRepository.save(empolyee);
//
//
//    }

    @Test
    public void testOTM() {
        Empolyee empolyee = new Empolyee();
        empolyee.setName("老王1");
        empolyee.setJob("测试");
        Empolyee empolyee2 = new Empolyee();
        empolyee2.setName("老陈1");
        empolyee2.setJob("开发");

        Department department = new Department();
        department.setName("产品五部");

        department.getEmpolyees().add(empolyee);
        department.getEmpolyees().add(empolyee2);

        departmentRepository.save(department);
//        empolyeeRepository.save(empolyee);


    }





}
