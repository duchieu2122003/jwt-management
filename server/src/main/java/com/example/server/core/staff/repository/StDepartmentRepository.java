package com.example.server.core.staff.repository;

import com.example.server.core.staff.model.response.StDepartmentsResponse;
import com.example.server.repositoty.DepartmentsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Repository
public interface StDepartmentRepository extends DepartmentsRepository {

    Optional<StDepartmentsResponse> findDepartmentsById(String id);
}
