package cn.cst.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private String id;
    private String roomIdNum;
    private String roomCoverUrl;
    @NotEmpty(message = "房间名称不能为空")
    private String roomName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdTime;
    private String status;
    private int roomSpace;
    private int onLinePlayerNum;
    private String userId;
    private String username;
    @NotEmpty(message = "房间描述不能为空")
    private String roomDescription;

}
