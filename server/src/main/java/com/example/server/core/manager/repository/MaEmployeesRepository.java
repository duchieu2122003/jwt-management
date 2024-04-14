package com.example.server.core.manager.repository;

import com.example.server.core.manager.model.response.MaEmployeesMissionsResponse;
import com.example.server.core.manager.model.response.MaEmployeesResponse;
import com.example.server.entity.Employees;
import com.example.server.repositoty.EmployeesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duchieu212
 */
@Repository
public interface MaEmployeesRepository extends EmployeesRepository {

    @Query(value = """
            SELECT e.id, 
                CONCAT(e.firstName, ' ', e.lastName) as full_name,
                e.email
             FROM Employees e WHERE e.departments IS NULL
            """)
    List<MaEmployeesResponse> getAllEmployeesNotDepartment();

    @Query(value = """
            SELECT e
             FROM Employees e WHERE e.departments IS NULL
            """)
    List<Employees> getListEmployeesNotDepartment();

    @Query(value = """
            SELECT e FROM Employees e WHERE e.departments.id LIKE :idDepartment
            """)
    List<Employees> getEmployeesByIdDepartment(String idDepartment);

    @Query(value = """
            SELECT e.id as id,
                   e.code as code,
                  CONCAT(e.first_name, ' ', e.last_name) as full_name,
                  e.email as email,
                  e.birthday as birthday,
                  e.gender as gender,
                  CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
                  e.status as status,
                  GROUP_CONCAT(DISTINCT m.name SEPARATOR ',') as full_missions
                  FROM employees e
                  LEFT JOIN employees_missions em ON em.employees_id = e.id
                  LEFT JOIN missions m on em.missions_id = m.id
                  WHERE e.department_id = :idDepartment
                  GROUP BY e.id, e.code, full_name, e.email, e.birthday, e.gender, 
                  full_address, e.status, e.last_name
                  ORDER BY e.last_name ASC               
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
                  GROUP_CONCAT(DISTINCT m.name SEPARATOR ',') as full_missions
                 FROM employees e
                 LEFT JOIN employees_missions em ON em.employees_id = e.id
                 INNER JOIN missions m on em.missions_id = m.id
                 WHERE e.id = :id
            """, nativeQuery = true)
    MaEmployeesMissionsResponse getMaEmployeeCustom(String id);

    @Query(value = """
            SELECT e.id as id,
                   e.code as code,
                  CONCAT(e.first_name, ' ', e.last_name) as full_name,
                  e.email as email,
                  e.birthday as birthday,
                  e.gender as gender,
                  CONCAT(e.address, ' - ', e.street, ' - ', e.city, ' - ', e.country) as full_address,
                  e.status as status,
                  GROUP_CONCAT(DISTINCT m.name SEPARATOR ',') as full_missions
                  FROM employees e
                  LEFT JOIN employees_missions em ON em.employees_id = e.id
                  INNER JOIN missions m on em.missions_id = m.id
                  WHERE e.department_id = :idDepartment
                  GROUP BY e.id, e.code, full_name, e.email, e.birthday, e.gender, 
                  full_address, e.status, e.last_name
                  ORDER BY e.last_name ASC               
             """, nativeQuery = true)
    List<MaEmployeesMissionsResponse> a(String idDepartment);
}
