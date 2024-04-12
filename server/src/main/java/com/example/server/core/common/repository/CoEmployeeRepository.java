package com.example.server.core.common.repository;

import com.example.server.core.common.model.response.CoDetailCustomEmployeeResponse;
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
public interface CoEmployeeRepository extends JpaRepository<Employees, String> {

    Optional<Employees> findByEmail(String email);

    @Query(value = """
                  SELECT e.id as id,
                         concat(e.first_name,' ',e.last_name) as full_name,
                         e.email as email,
                         e.birthday as birthday,
                         e.gender as gender,
                         e.address as address,
                         e.street as street,
                         e.city as city,
                         e.country as country
                  FROM employees e WHERE e.email = :email
            """, nativeQuery = true)
    Optional<CoDetailCustomEmployeeResponse> findEmployeeCustomByEmail(@Param("email") String email);

}
