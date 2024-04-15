package com.example.server.core.admin.repository;

import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.model.response.AdEmployeesCustomResponse;
import com.example.server.core.admin.model.response.AdEmployeesResponse;
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
             select distinct row_number() over (order by e.code ASC) as stt,
                    concat(e.first_name,e.last_name) as fullName,
                    e.code as code,
                    e.birthday as birthday,
                    concat(e.address,e.city,e.country) as fullAddress,
                    d.name as nameDepartment,
                    r.name
                    from employee e
             LEFT JOIN department d on e.department_id = d.id
             LEFT JOIN employee_roles er on e.id = er.employee_id
             LEFT JOIN role r on er.roles_id = r.id
            """, nativeQuery = true)
    Page<AdEmployeesResponse> getPageEmployee(Pageable pageable);

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

    Optional<Employees> findEmployeesByEmail(String email);

}
