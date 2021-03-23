package cn.cst.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    private Set<Address> addresses = new HashSet<>();



    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
