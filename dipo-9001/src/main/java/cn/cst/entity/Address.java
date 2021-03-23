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
@Table(name = "table_address")
public class Address {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;


    @Column(name = "detail")
    private String detail;

}