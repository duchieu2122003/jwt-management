package com.example.server.repositoty;

import com.example.server.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface EmployeesRepository extends JpaRepository<Employees, String> {

    Optional<Employees> findByEmail(String email);

    Optional<Employees> findEmployeesByCode(String code);

    @Query("""
            SELECT e FROM Employees e WHERE e.email = :email
            """)
    Optional<Employees> findEmployeesByEmailToLogin(@Param("email") String email);
}
