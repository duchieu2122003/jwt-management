package com.example.server.repositoty;

import com.example.server.entity.Employees;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository(EmployeesRepository.NAME)
@Primary
public interface EmployeesRepository extends JpaRepository<Employees, String> {

    String NAME = "BaseEmployeeRepository";

    @Query(value = """
            SELECT COUNT(1) FROM employee
            """, nativeQuery = true)
    Integer countSimpleEntityEmployee();

    Optional<Employees> findByEmail(String email);

    Optional<Employees> findEmployeesByCode(String code);

}
