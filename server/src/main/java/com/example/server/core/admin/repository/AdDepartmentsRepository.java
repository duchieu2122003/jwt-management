package com.example.server.core.admin.repository;

import com.example.server.entity.Departments;
import com.example.server.infrastructure.constant.StatusDepartment;
import com.example.server.repositoty.DepartmentsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface AdDepartmentsRepository extends DepartmentsRepository {

    List<Departments> findAllByStatus(StatusDepartment statusDepartment);

    Optional<Departments> findDepartmentsByName(String name);

    @Query(value = """
            SELECT e.id FROM employees e 
            WHERE e.department_id LIKE :id
            """, nativeQuery = true)
    List<String> getIdEmployeeOnIdDepartment(@Param("id") String id);
}
