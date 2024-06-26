package com.example.server.core.manager.service;

import com.example.server.core.manager.model.response.MaEmployeesResponses;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaEmployeesService {

    List<MaEmployeesResponses> getAllEmployeesNotDepartment();


    boolean deleteMissionsAnDepartmentForEmployees(String id);
}
