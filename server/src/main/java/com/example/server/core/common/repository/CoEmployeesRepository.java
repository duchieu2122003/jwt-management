package com.example.server.core.common.repository;

import com.example.server.core.common.model.response.CoDetailCustomEmployeeResponse;
import com.example.server.core.common.model.response.CoEmployeesInformationResponse;
import com.example.server.entity.Employees;
import com.example.server.repositoty.EmployeesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface CoEmployeesRepository extends EmployeesRepository {

//    Optional<Employees> findByEmail(String email);

    @Query("SELECT e FROM Employees e LEFT JOIN FETCH e.missions WHERE e.email = :email")
    Optional<Employees> findByEmailWithFetchMissions(@Param("email") String email);

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

    @Query(value = """
            SELECT e.id as id,
                   e.code as code,
                   e.first_name as first_name,
                   e.last_name as last_name,
                   e.email as email,
                   e.birthday as birthday,
                   e.gender as gender,
                   e.address as address,
                   e.street as street,
                   e.city as city,
                   e.country as country,
                   e.status as status
            FROM employees e
                     LEFT JOIN employees_missions em ON em.employees_id = e.id
                     LEFT JOIN missions m on em.missions_id = m.id
            WHERE e.email = :email
            """, nativeQuery = true)
    Optional<CoEmployeesInformationResponse> findEmployeesMyAuth(String email);

    Optional<Employees> findEmployeesByEmail(String email);
}