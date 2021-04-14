package cn.cst.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocketUser implements Serializable {

    private String userId;
    private String token;
    private String sessionId;
    private String username;
    private String roomId;
}
