package cn.cst.entity;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
public class SocketUser implements Serializable {

    private String userId;
    private String token;
    private String sessionId;
    private String username;
    private String roomId;
}
