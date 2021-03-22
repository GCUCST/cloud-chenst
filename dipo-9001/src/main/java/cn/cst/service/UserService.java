package cn.cst.service;

import cn.cst.dao.UserRepository;
import cn.cst.entity.User;
import cn.cst.exception.LoginException;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User loginUser(User user) {
        User u = userRepository.findByUsername(user.getUsername());
        System.out.println(u.toString());
        if (!checkPassword(user.getPassword(),u.getPassword())) {
            throw new LoginException("密码错误", "400");
        } else return u;
    }


    public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        //加密后的字符串
        byte[] digest = md5.digest(str.getBytes("utf-8"));
        String s = Base64Utils.encodeToString(digest);
        return s;
    }


    public boolean checkPassword(String newPassword,String existPassword){
        try {
            if(encoderByMd5(newPassword).equals(existPassword))
                return true;
            else
                return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }



}
