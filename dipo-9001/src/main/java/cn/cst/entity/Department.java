package cn.cst.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_department")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Department {

    private String id;
    private String name;


    @GeneratedValue(generator = "jpa-uuid")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Set<Empolyee> empolyees = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "depart_id")
    public Set<Empolyee> getEmpolyees() {
        return empolyees;
    }

    public void setEmpolyees(Set<Empolyee> empolyees) {
        this.empolyees = empolyees;
    }
}
