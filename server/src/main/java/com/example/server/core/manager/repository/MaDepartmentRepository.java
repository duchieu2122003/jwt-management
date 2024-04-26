package com.example.server.core.manager.repository;

import com.example.server.entity.Departments;
import com.example.server.repositoty.DepartmentsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface MaDepartmentRepository extends DepartmentsRepository {

    Optional<Departments> findDepartmentsByName(String name);

    @Query(value = """
                    SELECT d.*
                     FROM departments d 
                     INNER JOIN employees e ON e.department_id = d.id
                     WHERE e.email= :emailUserCurrent
            """, nativeQuery = true)
    Optional<Departments> getDepartmentByUserCurrent(@Param("emailUserCurrent") String emailUserCurrent);
}
