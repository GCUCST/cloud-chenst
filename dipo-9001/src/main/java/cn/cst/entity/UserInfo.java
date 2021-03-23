package cn.cst.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "table_user_info")
public class UserInfo {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    private String id;

    @Column(name="gender")
    String gender;

    @Column(name="wechat")
    String wechat;

    @Column(name="status")
    String status;


    @Column(name="age")
    Integer age;


}
