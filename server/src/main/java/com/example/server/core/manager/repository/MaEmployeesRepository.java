package com.example.server.core.manager.repository;

import com.example.server.core.manager.model.response.MaEmployeeMissionGetResponse;
import com.example.server.core.manager.model.response.MaEmployeesMissionUpdateResponse;
import com.example.server.core.manager.model.response.MaEmployeesMissionsResponse;
import com.example.server.entity.Employees;
import com.example.server.repositoty.EmployeesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface MaEmployeesRepository extends EmployeesRepository {

    @Query(value = """
            SELECT e
             FROM Employees e WHERE e.departments IS NULL and e.role = 'STAFF'
            """)
    List<Employees> getAllEmployeesNotDepartment();

    @Query(value = """
            SELECT e
             FROM Employees e WHERE e.id = :id
            """)
    Optional<Employees> getEmployeesById(@Param("id") String id);

    @Query(value = """
            SELECT e
             FROM Employees e WHERE e.departments IS NULL
            """)
    List<Employees> getListEmployeesNotDepartment();

    @Query(value = """
                        SELECT em.employee_id as employee_id,
                                em.mission_id as mission_id
                         FROM employees_missions em WHERE em.employee_id = :id
            """, nativeQuery = true)
    List<MaEmployeeMissionGetResponse> getMissionEmployeeByIdEmployee(@Param("id") String id);

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
                  COALESCE(GROUP_CONCAT(DISTINCT m.name SEPARATOR ', '),'')as full_missions,
                   IFNULL(SUM(IFNULL(m.salary, 0)), 0) as total_salary
                  FROM employees e
                  LEFT JOIN employees_missions em ON em.employee_id = e.id
                  LEFT JOIN missions m on em.mission_id = m.id
                  WHERE e.department_id = :idDepartment
                  GROUP BY e.id, e.code, full_name, e.email, e.birthday, e.gender, 
                  full_address, e.status, e.last_name            
             """, nativeQuery = true)
    List<MaEmployeesMissionsResponse> getMaListEmployeeCustom(String idDepartment);


    @Query(value = """
            SELECT e.id as id,
                   e.code as code,
                  CONCAT(e.first_name, ' ', e.last_name) as full_name,
                  e.email as email,
                  e.birthday as birthday,
                  e.gender as gender,
                  CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
                  e.status as status,
                  COALESCE(GROUP_CONCAT(DISTINCT m.name SEPARATOR ', '),'') as full_missions,
                  IFNULL(SUM(IFNULL(m.salary, 0)), 0) as total_salary
                 FROM employees e
                 LEFT JOIN employees_missions em ON em.employee_id = e.id
                 LEFT JOIN missions m on em.mission_id = m.id
                 WHERE e.id = :id
            """, nativeQuery = true)
    Optional<MaEmployeesMissionUpdateResponse> getMaEmployeeCustom(String id);
}
