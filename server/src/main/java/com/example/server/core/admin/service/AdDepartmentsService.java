package com.example.server.core.admin.service;

import com.example.server.core.admin.model.response.AdDepartmentsResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface AdDepartmentsService {

    List<AdDepartmentsResponse> getAllDepartmentActive();
}
