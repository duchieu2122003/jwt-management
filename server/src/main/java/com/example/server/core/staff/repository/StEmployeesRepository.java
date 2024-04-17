package com.example.server.core.staff.repository;

import com.example.server.core.staff.model.response.StEmployeesCustomResponse;
import com.example.server.repositoty.EmployeesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duchieu212
 */
@Repository
public interface StEmployeesRepository extends EmployeesRepository {

    @Query(value = """
             SELECT DISTINCT ROW_NUMBER() over (ORDER BY e.code ASC)  as stt,
                           e.id as id,
                   e.code as code,
                  CONCAT(e.first_name, ' ', e.last_name) as full_name,
                  e.email as email,
                  e.birthday as birthday,
                  e.gender as gender,
                  CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
                  e.status as status,
                  GROUP_CONCAT(DISTINCT m.name SEPARATOR ',') as full_missions
                  FROM employees e
                  LEFT JOIN employees_missions em ON em.employee_id = e.id
                  LEFT JOIN missions m on em.mission_id = m.id
                  WHERE e.department_id = :id
                  GROUP BY e.id, e.code, full_name, e.email, e.birthday, e.gender, 
                  full_address, e.status, e.last_name   
            """, nativeQuery = true)
    List<StEmployeesCustomResponse> getAllEmployeesByDepartmentId(@Param("id") String id);

}
