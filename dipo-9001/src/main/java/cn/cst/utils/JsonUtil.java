
package cn.cst.utils;

import cn.cst.entity.MessageTemplate;
import cn.cst.entity.SocketUser;
import cn.cst.entity.User;
import cn.cst.exception.CustomException;
import com.alibaba.fastjson.JSONObject;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpSession;

@UtilityClass
public class JsonUtil<T> {


    public SocketUser getSocketUser(String jsonStr) {
        SocketUser socketUser = JSONObject.parseObject(jsonStr, SocketUser.class);
        return socketUser;
    }

    public String getMsgJsonStr(MessageTemplate messageTemplate) {
        String targetStr = JSONObject.toJSONString(messageTemplate);
        return targetStr;
    }

}
