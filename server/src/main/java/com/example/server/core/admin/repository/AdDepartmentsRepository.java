package com.example.server.core.admin.repository;

import com.example.server.core.admin.response.AdDepartmentsResponse;
import com.example.server.infrastructure.constant.StatusDepartment;
import com.example.server.repositoty.DepartmentsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duchieu212
 */
@Repository
public interface AdDepartmentsRepository extends DepartmentsRepository {

    List<AdDepartmentsResponse> findAllByStatus(StatusDepartment statusDepartment);
}
