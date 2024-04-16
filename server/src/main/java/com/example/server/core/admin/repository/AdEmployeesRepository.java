package com.example.server.core.admin.repository;

import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.model.response.AdEmployeesCustomResponse;
import com.example.server.core.admin.model.response.AdEmployeesDetailResponse;
import com.example.server.entity.Employees;
import com.example.server.repositoty.EmployeesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface AdEmployeesRepository extends EmployeesRepository {

    @Query(value = """
            SELECT DISTINCT ROW_NUMBER() over (ORDER BY e.code ASC)  as stt,
                            e.id  as id,
                            e.code  as code,
                            CONCAT(e.first_name, ' ', e.last_name) as full_name,
                            e.email as email,
                            e.birthday as birthday,
                            e.gender as gender,
                            CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
                            e.status as status
            FROM employees e
            WHERE (:#{#request.code} IS NULL OR :#{#request.code} LIKE '' OR e.code LIKE %:#{#request.code}%)
                AND (:#{#request.name} IS NULL OR :#{#request.name} LIKE '' OR CONCAT(e.first_name, ' ', e.last_name) LIKE %:#{#request.name}%)
                AND (:#{#request.email} IS NULL OR :#{#request.email} LIKE '' OR e.email LIKE %:#{#request.email}% )
                AND (:#{#request.city} IS NULL OR :#{#request.city} LIKE '' OR %:#{#request.city}% LIKE e.city)
                AND (:#{#request.status} IS NULL OR :#{#request.status} LIKE '' OR :#{#request.status} LIKE e.status)
                        """, nativeQuery = true)
    Page<AdEmployeesCustomResponse> getAdPageEmployeeCustom(@Param("request") AdEmployeesCustomRequest request, Pageable pageable);

    @Query(value = """
            SELECT e.id                                                                as id,
                  e.code                                                              as code,
                  CONCAT(e.first_name, ' ', e.last_name)                              as full_name,
                  e.email                                                             as email,
                  e.birthday                                                          as birthday,
                  e.gender                                                            as gender,
                  CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
                  e.status                                                            as status
            FROM employees e
            WHERE e.id = :#{#id} 
            """, nativeQuery = true)
    AdEmployeesCustomResponse findEmployeesCustomById(@Param("id") String id);

    @Query(value = """
            SELECT DISTINCT e.id  as id,
                  e.code as code,
                e.first_name as first_name, 
                e.last_name as last_name,
                e.email as email,
                e.birthday as birthday,
                e.gender  as gender,
                e.address as address,
                e.street as street,
                e.city as city,
                e.country as country,
                e.status as status,
                e.role as role,
                 COALESCE(e.department_id, '') as idDepartments
            FROM employees e
            WHERE e.id = :#{#id} 
            """, nativeQuery = true)
    AdEmployeesDetailResponse findEmployeesDetailById(@Param("id") String id);

    Optional<Employees> findEmployeesByEmail(String email);

}
