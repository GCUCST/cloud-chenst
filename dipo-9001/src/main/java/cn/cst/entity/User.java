package cn.cst.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



// 不要使用   @Data   因为其中的toString导致StackOverflow!!!!!!!!!!!!
// 注解写在  get


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "table_user")
@Proxy(lazy = false)
public class User{

    private String id;


    private String username;


    private String password;




    UserInfo userInfo;

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
    @Column(name = "username",nullable = true,unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotEmpty
    @Column(name = "password",nullable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //级联All操作，饿汉模式（直接拉取对应的被维护方）
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    //维护关系的一方生成一个自定义的键（外键），值对应被维护关系的一方的主键，双向一对一需要指明unique=true
    @JoinColumn(name = "fk_user_info_id",unique=true)
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
