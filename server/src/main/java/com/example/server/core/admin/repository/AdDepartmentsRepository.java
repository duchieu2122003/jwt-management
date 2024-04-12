package com.example.server.core.admin.repository;

import com.example.server.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author duchieu212
 */
@Repository
public interface AdDepartmentsRepository extends JpaRepository<Departments, String> {
}
