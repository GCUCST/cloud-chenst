//package cn.cst;
////
//
//import cn.cst.dao.UserInfoRepository;
//import cn.cst.dao.UserRepository;
//import cn.cst.entity.User;
//import cn.cst.entity.UserInfo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.regex.Pattern;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest
//
//public class AppTest {
//
//    @Test
//   public void testA(){
//        String pattern = "^image/.*";
//
//        System.out.println(Pattern.matches(pattern, "image/jpeg"));
//        System.out.println(Pattern.matches(pattern, "image/png"));
//        System.out.println(Pattern.matches(pattern, "text/html"));
//        System.out.println(Pattern.matches(pattern, "^image/.*"));
//        System.out.println(Pattern.matches(pattern, "abct"));
//        System.out.println(Pattern.matches(pattern, "image"));
//        System.out.println(Pattern.matches(pattern, "^image"));
//
//    }
//
//
//
//
//    @Autowired
//    private UserInfoRepository userInfoRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testOTO() {
//        User user = new User();
//        user.setUsername("陈少桐5");
//        user.setPassword("10086234");
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.setStatus("OK5");
//
//        user.setUserInfo(userInfo);
//
//        userRepository.save(user);
//
////        userInfo.setUser(user);
////        userInfoRepository.save(userInfo);
//    }
//
//    @Test
//    public void testOTO2() {
//        User u = userRepository.getOne("2c9e608178658934017865894c620000");
//        System.err.println(u.getUserInfo().getStatus());
//    }
//
//    @Test
//    public void testOTO3() {
//        UserInfo u = userInfoRepository.getOne("2c9e608178658934017865894c6b0001");
//        System.out.println(u.getUser().getUsername());
//    }
//
////
////
////    //@RestController
////    //@RequestMapping(value = "api/user")
////    //public class UserController {
////    //
////    //    @Autowired
////    //    private UserService userService;
////    //
////    //    @PostMapping(path = "")
////    //    public ResponseEntity<User> addUser(@RequestBody User user) {
////    //        User userSave = userService.save(user);
////    //        return ResponseEntity.ok(userSave);
////    //    }
////    @Autowired
////    UserController userController;
////
////    @Autowired
////    LoginController loginController;
////
////    @Test
////    public void testReg() {
////        User user = User.builder().username("cst").password("123").build();
////        userController.addUser(user);
////    }
////
////    @Test
////    public void testLogin() {
////        User user = User.builder().username("cst").password("123").build();
////        loginController.loginUser(user, null);
////    }
////
////    @Autowired
////    UserRepository userRepository;
////    @Autowired
////    AddressRepository addressRepository;
////
//////    @Test
//////    public void testManyToOne() {
//////        User user = User.builder().username("CST").password("123").build();
//////        Address address = Address.builder().detail("AAA").user(user).build();
//////        Address address2 = Address.builder().detail("BBB").user(user).build();
//////        userRepository.save(user);
//////        addressRepository.save(address);
//////        addressRepository.save(address2);
//////
//////    }
//////    @Test
//////    public void testManyToOneFind() {
//////        List<Address> all = addressRepository.findAll();
//////        for (Address address:all){
//////            System.out.println(address.getUser().getUsername());
//////        }
//////    }
////
//////    @Test
//////    public void testOneToMany() {
//////        Address address = Address.builder().detail("AAA").build();
//////        Address address2 = Address.builder().detail("BBB").build();
//////        User user = User.builder().username("CST").password("123").addresses(new HashSet<>()).build();
//////        System.out.println(user.getAddresses().isEmpty());
//////        user.getAddresses().add(address);
//////        user.getAddresses().add(address2);
//////        addressRepository.save(address);
//////        addressRepository.save(address2);
//////        userRepository.save(user);
//////    }
//////    @Test
//////    public void testOneToManyFind() {
//////        List<User> all = userRepository.findAll();
//////        for (User u:all){
//////            System.out.println(u.getAddresses());
//////        }
//////    }
////
////    @Test
////    public void testOneToManyToOne() {
////        Address address = Address.builder().detail("AAA").build();
////        Address address2 = Address.builder().detail("BBB").build();
////        User user = User.builder().username("CST").password("123").addresses(new HashSet<>()).build();
////        user.getAddresses().add(address);
////        user.getAddresses().add(address2);
////        address.setUser(user);
////        address2.setUser(user);
////        userRepository.save(user);
////    }
////    @Test
////    public void testOneToManyToOneFind() {
////        List<User> all = userRepository.findAll();
////        for (User u:all){
////            System.out.println(u.getAddresses());
////        }
////    }
////    @Test
////    public void testOneToManyToOneFindA() {
////        List<Address> all = addressRepository.findAll();
////        for (Address u:all){
////            System.out.println(u.getUser());
////        }
////    }
////
////    @Autowired
////    DepartmentRepository departmentRepository;
////
////    @Autowired
////    EmpolyeeRepository empolyeeRepository;
////
//////    @Test
//////    public void testMTO() {
//////        Empolyee empolyee = new Empolyee();
//////        empolyee.setName("老王");
//////        empolyee.setJob("测试");
//////
//////        Department department = new Department();
//////        department.setName("产品二部");
//////
//////        empolyee.setDepartment(department);
//////
////////        departmentRepository.save(department);
//////        empolyeeRepository.save(empolyee);
//////
//////
//////    }
////
////    @Test
////    public void testOTM() {
////        Empolyee empolyee = new Empolyee();
////        empolyee.setName("老王1");
////        empolyee.setJob("测试");
////        Empolyee empolyee2 = new Empolyee();
////        empolyee2.setName("老陈1");
////        empolyee2.setJob("开发");
////
////        Department department = new Department();
////        department.setName("产品五部");
////
////        department.getEmpolyees().add(empolyee);
////        department.getEmpolyees().add(empolyee2);
////
////        departmentRepository.save(department);
//////        empolyeeRepository.save(empolyee);
////
////
////    }
////
////
////
////
////
//}
//
//
