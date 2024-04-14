package com.example.server.core.manager.service;

import com.example.server.core.manager.model.request.MaDepartmentCreateRequest;
import com.example.server.core.manager.model.request.MaDepartmentUpdateRequest;
import com.example.server.core.manager.model.response.MaDepartmentResponse;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaDepartmentService {

    List<MaDepartmentResponse> getAll();

    MaDepartmentResponse detail(String id);

    MaDepartmentResponse create(@Valid MaDepartmentCreateRequest request);

    MaDepartmentResponse update(@Valid MaDepartmentUpdateRequest request);

    boolean delete(String id);
}
