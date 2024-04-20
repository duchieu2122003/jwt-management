package com.example.server.core.admin.service;

import com.example.server.core.admin.model.request.AdDepartmentCreateRequest;
import com.example.server.core.admin.model.request.AdDepartmentUpdateRequest;
import com.example.server.core.admin.model.response.AdDepartmentsGetResponse;
import com.example.server.core.admin.model.response.AdDepartmentsResponse;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author duchieu212
 */
public interface AdDepartmentsService {

    List<AdDepartmentsResponse> getAllDepartmentActive();

    List<AdDepartmentsGetResponse> getAll();

    AdDepartmentsGetResponse detail(String id);

    AdDepartmentsGetResponse create(@Valid AdDepartmentCreateRequest request);

    AdDepartmentsGetResponse update(@Valid AdDepartmentUpdateRequest request);

    boolean delete(String id);

}
