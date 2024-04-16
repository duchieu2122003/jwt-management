package com.example.server.core.staff.service;

import com.example.server.core.staff.model.response.StEmployeesCustomResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface StEmployeesService {

    List<StEmployeesCustomResponse> getAllByDepartmentId();
}
