
package cn.cst.utils;

import cn.cst.entity.MessageTemplate;
import cn.cst.entity.SocketUser;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class MessageUtil<T> {


    public MessageTemplate buildMessage(String content,SocketUser socketUser){
      return MessageTemplate.builder()
              .id(UUID.randomUUID().toString())
              .avatarUrl("https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg")
              .userId(socketUser.getUserId()) //这个是username == userId
              .nickName(socketUser.getUserId())//目前没有昵称配置
              .createTime(DateTimeUtil.getNowDateTime())
              .msgContent(content)
              .like(0)
              .role("主播") //先默认主播
              .build();
    }

    public MessageTemplate systemMessage(String content){
        return MessageTemplate.builder()
                .id(UUID.randomUUID().toString())
                .avatarUrl("https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg")
                .userId("system_001") //这个是username == userId
                .nickName("system_001")//目前没有昵称配置
                .createTime(DateTimeUtil.getNowDateTime())
                .msgContent(content)
                .like(0)
                .role("系统") //先默认主播
                .build();
    }




}
