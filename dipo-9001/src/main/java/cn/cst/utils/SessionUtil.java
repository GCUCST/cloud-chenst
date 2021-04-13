
package cn.cst.utils;

import cn.cst.entity.User;
import cn.cst.exception.CustomException;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpSession;

@UtilityClass
public class SessionUtil {

    public User getUser(HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user==null){
            throw new CustomException(401,"请先登录...");
        }
        return user;
    }

}
