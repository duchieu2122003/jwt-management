package com.example.server.core.admin.repository;

import com.example.server.entity.Departments;
import com.example.server.infrastructure.constant.StatusDepartment;
import com.example.server.repositoty.DepartmentsRepository;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface AdDepartmentsRepository extends DepartmentsRepository {

    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    List<Departments> findAllByStatus(StatusDepartment statusDepartment);

    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    Optional<Departments> findDepartmentsByName(String name);

    @Override
    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    List<Departments> findAll();

    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    @Query(value = """
            SELECT e.id FROM employees e 
            WHERE e.department_id LIKE :id
            """, nativeQuery = true)
    List<String> getIdEmployeeOnIdDepartment(@Param("id") String id);

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    Departments save(Departments departments);

    @Override
    @Transactional
    @Modifying
    void delete(Departments departments);

//    @Override
//    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
//    List<Departments> findAll();
}
