package com.example.server.repositoty;

import com.example.server.entity.Departments;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository(DepartmentsRepository.NAME)
@Primary
public interface DepartmentsRepository extends JpaRepository<Departments, String> {
    String NAME = "BaseDepartmentRepository";

    Optional<Departments> findByName(String name);
}
