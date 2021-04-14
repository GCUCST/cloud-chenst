package cn.cst.config;


import cn.cst.entity.MessageTemplate;
import com.alibaba.fastjson.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * definition for our encoder
 *
 * @编写人: 夏小雪 日期:2015年6月14日 时间:上午11:58:23
 */
public class ServerEncoder implements Encoder.Text<MessageTemplate> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public String encode(MessageTemplate messageTemplate) throws EncodeException {
        return JSONObject.toJSONString(messageTemplate);
    }

}
