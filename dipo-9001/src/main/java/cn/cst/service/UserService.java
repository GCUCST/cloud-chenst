package cn.cst.service;

import cn.cst.dao.UserInfoRepository;
import cn.cst.dao.UserRepository;
import cn.cst.entity.User;
import cn.cst.entity.UserInfo;
import cn.cst.exception.LoginException;
import cn.cst.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private UserInfoRepository userInfoRepository;


    public User loginUser(User user) {
        User u = userRepository.findByUsername(user.getUsername());
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

        //判断用户存在不
        User database_user = userRepository.findByUsername(user.getUsername());
        if (database_user != null) {
            throw new UserExistException("400", "用户已经存在");
        }

        UserInfo userInfo = userInfoRepository.save(UserInfo.builder().status("OK").build());
        user.setUserInfoId(userInfo.getId());
        user.setPassword(encoderByMd5(user.getPassword()));
        User userSave = userRepository.save(user);

        return userSave;
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
