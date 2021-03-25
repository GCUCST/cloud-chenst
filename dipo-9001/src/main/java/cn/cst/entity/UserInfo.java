package cn.cst.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "table_user_info")
@Proxy(lazy = false)
public class UserInfo implements Serializable {


    private String id;


    String gender;


    String wechat;


    String status;


    Integer age;

    User user;


    String personalWebSite;

    String introduction;

    public String getPersonalWebSite() {
        return personalWebSite;
    }

    public void setPersonalWebSite(String personalWebSite) {
        this.personalWebSite = personalWebSite;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
    @Column(name="gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name="wechat")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    @Column(name="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name="age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    //双向一对一需要 配置mappedBy.值为关系维护方（另一方）的一个属性(对应当前类的，通过他去找fk)
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "userInfo",cascade={CascadeType.ALL} )
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
