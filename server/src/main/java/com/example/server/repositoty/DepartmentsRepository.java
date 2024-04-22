package com.example.server.repositoty;

import com.example.server.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author duchieu212
 */
@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, String> {

}
