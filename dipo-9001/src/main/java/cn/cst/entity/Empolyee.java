package cn.cst.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_empolyee")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Empolyee {

    private String id;
    private String name;
    private String job;

//    private Department department;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "department_id")
//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }

    @Column(name = "job")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

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
}
