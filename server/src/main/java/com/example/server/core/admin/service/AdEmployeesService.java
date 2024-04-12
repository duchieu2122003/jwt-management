package com.example.server.core.admin.service;

import com.example.server.core.admin.request.AdEmployeesCreateRequest;
import com.example.server.core.admin.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.request.AdEmployeesUpdateRequest;
import com.example.server.core.admin.response.AdEmployeesCustomResponse;
import com.example.server.entity.Employees;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author duchieu212
 */
public interface AdEmployeesService {

    List<Employees> getAll();

    Page<AdEmployeesCustomResponse> getAdPageEmployeeCustom(AdEmployeesCustomRequest request);

    AdEmployeesCustomResponse create(AdEmployeesCreateRequest request);

    AdEmployeesCustomResponse update(AdEmployeesUpdateRequest request);

    AdEmployeesCustomResponse detail(String id);

    Boolean delete(String id);

    Boolean setEmployeeQuit(String id);

}
