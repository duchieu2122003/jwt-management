package com.example.server.core.manager.service;

import com.example.server.core.manager.model.response.MaEmployeesResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaEmployeesService {

    List<MaEmployeesResponse> getAllEmployeesNotDepartment();
}
