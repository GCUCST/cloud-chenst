package cn.cst.dao;

import cn.cst.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
    Person findByNameStartsWith(String name);
}
