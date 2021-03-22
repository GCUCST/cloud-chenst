package cn.cst.entity;

import com.google.inject.internal.asm.$Type;
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
@Table(name = "table_user")
public class User {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    private String id;
    @NotEmpty
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
