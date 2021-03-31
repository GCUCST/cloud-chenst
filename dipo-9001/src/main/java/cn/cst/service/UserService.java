package cn.cst.service;

//import cn.cst.dao.UserInfoRepository;

import cn.cst.dao.UserRepository;
import cn.cst.entity.User;
import cn.cst.entity.UserInfo;
import cn.cst.exception.LoginException;
import cn.cst.exception.UserExistException;
import cn.cst.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private UserInfoRepository userInfoRepository;

    public User loginUser(User user) {
        User u = userRepository.findByUsername(user.getUsername());
        if(u==null){
            throw new UserNotFoundException( "404","找不到该用户");
        }
        System.out.println(u.toString());
        if (!checkPassword(user.getPassword(), u.getPassword())) {
            throw new LoginException("密码错误", "400");
        } else return u;
    }


    public static String encoderByMd5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(str.getBytes("utf-8"));
            String s = Base64Utils.encodeToString(digest);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkPassword(String newPassword, String existPassword) {
        if (encoderByMd5(newPassword).equals(existPassword))
            return true;
        else
            return false;
    }

    @Transactional
    public User save(User user) {
        User database_user = userRepository.findByUsername(user.getUsername());
        if (database_user != null) {
            throw new UserExistException( "400","用户已存在");
        }
        user.setPassword(encoderByMd5(user.getPassword()));
        UserInfo ok = UserInfo.builder().status("OK").build();
        user.setUserInfo(ok);
        User userSave = userRepository.save(user);
        return userSave;
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User updateUserInfo(String username, String wechat, String personalWebSite, String introduction) {

        User userFromDB = userRepository.findByUsername(username);
        if(userFromDB==null){
            throw new UserNotFoundException("找不到目标用户。"+username,"404");
        }
        UserInfo userInfo = userFromDB.getUserInfo();
        userInfo.setWechat(wechat);
        userInfo.setIntroduction(introduction);
        userInfo.setPersonalWebSite(personalWebSite);
        userFromDB.setUserInfo(userInfo);
        User save = userRepository.save(userFromDB);
        return save;

    }
}
