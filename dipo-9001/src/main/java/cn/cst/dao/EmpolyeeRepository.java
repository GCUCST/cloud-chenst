package cn.cst.dao;

import cn.cst.entity.Empolyee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpolyeeRepository extends JpaRepository<Empolyee, String> {
}
