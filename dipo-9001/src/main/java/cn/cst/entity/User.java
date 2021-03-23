package cn.cst.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "table_user")
public class User {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    private String id;
    @NotEmpty
    @Column(name = "username",nullable = true,unique = true)
    private String username;
    @NotEmpty
    @Column(name = "password",nullable = true)
    private String password;

    @Column(name = "user_info_id",nullable = true)
    private String userInfoId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_info_id",insertable=false, updatable=false)
    private UserInfo userInfo;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Address> address;



}
