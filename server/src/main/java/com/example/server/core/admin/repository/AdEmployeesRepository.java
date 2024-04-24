package com.example.server.core.admin.repository;

import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
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
    //DISTINCT ROW_NUMBER() over (ORDER BY e.code ASC)  as stt,
//                            e.id  as id,
//                            e.code  as code,
//                            CONCAT(e.first_name, ' ', e.last_name) as full_name,
//                            e.email as email,
//                            e.birthday as birthday,
//                            e.gender as gender,
//                            CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
//                            e.status as status,
//                            e.role as role,
//                            COALESCE(d.name,'') as department
    // LEFT JOIN departments d ON d.id = e.department_id
    @Query(value = """
            SELECT *
            FROM employees e
            WHERE (:#{#request.code} IS NULL OR :#{#request.code} = '' OR %:#{#request.code}% LIKE e.code)
                AND (:#{#request.name} IS NULL OR :#{#request.name} = '' OR %:#{#request.name}% LIKE CONCAT(e.first_name, ' ', e.last_name))
                AND (:#{#request.email} IS NULL OR :#{#request.email} = '' OR %:#{#request.email}% LIKE e.email )
                AND (:#{#request.city} IS NULL OR :#{#request.city} = '' OR %:#{#request.city}% LIKE e.city)
                AND (:#{#request.status} IS NULL OR :#{#request.status} = '' OR :#{#request.status} LIKE e.status)
                AND (:#{#request.role} IS NULL OR :#{#request.role} = '' OR :#{#request.role} LIKE e.role)
                AND e.role <> 'ADMIN'
                        """, nativeQuery = true)
    Page<Employees> getAdPageEmployeeCustom(@Param("request") AdEmployeesCustomRequest request, Pageable pageable);

    @Query(value = """
            SELECT *
            FROM employees e
            WHERE e.id = :#{#id} 
            """, nativeQuery = true)
    Employees findEmployeesCustomById(@Param("id") String id);

    @Query(value = """
            SELECT *
            FROM employees e
            WHERE e.id = :#{#id} 
            """, nativeQuery = true)
    Employees findEmployeesDetailById(@Param("id") String id);

    Optional<Employees> findEmployeesByEmail(String email);

    @Query(value = """
            SELECT COUNT(e.id) FROM employees e INNER JOIN departments d ON d.id = e.department_id
            WHERE d.id = :departmentId and e.role LIKE 'MANAGER'
            """, nativeQuery = true)
    Integer countManagerInDepartment(@Param("departmentId") String departmentId);

    @Query(value = """
            SELECT COUNT(e.id) FROM employees e
            WHERE e.role LIKE 'ADMIN'
            """, nativeQuery = true)
    Integer countAdminSystem();

}
