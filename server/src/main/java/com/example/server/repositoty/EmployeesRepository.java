package com.example.server.repositoty;

import com.example.server.core.common.model.response.CoEmployeesLoginResponse;
import com.example.server.entity.Employees;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("""
            SELECT e.id as id,
                    e.email as email,
                    e.firstName as first_name,
                    e.lastName as last_name,
                    e.role as role,
                    e.birthday as birthday,
                    e.password as password
             FROM Employees e WHERE e.email = :email
            """)
    Optional<CoEmployeesLoginResponse> findEmployeesByEmailToLogin(@Param("email") String email);
}
