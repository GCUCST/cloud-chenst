package cn.cst.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "table_notes")
public class Note implements Serializable {



    private String id;
    private String key;
    private String value;
    private String userId;
    private LocalDateTime createdTime;

    @Column(name = "createdTime",nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @NotEmpty
    @Column(name = "userId",nullable = true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @Column(name = "key_1",nullable = true)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    @NotEmpty
    @Column(name = "value_1",nullable = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}