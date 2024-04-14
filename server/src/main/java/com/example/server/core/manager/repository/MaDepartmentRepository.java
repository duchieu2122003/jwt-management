package com.example.server.core.manager.repository;

import com.example.server.entity.Departments;
import com.example.server.repositoty.DepartmentsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface MaDepartmentRepository extends DepartmentsRepository {

    Optional<Departments> findDepartmentsByName(String name);
}
