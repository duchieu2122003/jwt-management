package com.example.server.core.admin.service;

import com.example.server.core.admin.model.request.AdEmployeesCreateRequest;
import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.model.request.AdEmployeesUpdateRequest;
import com.example.server.core.admin.model.response.AdEmployeesCustomResponse;
import com.example.server.core.admin.model.response.AdEmployeesDetailResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

/**
 * @author duchieu212
 */
public interface AdEmployeesService {

    Page<AdEmployeesCustomResponse> getAdPageEmployeeCustom(final AdEmployeesCustomRequest request);

    AdEmployeesCustomResponse create(@Valid AdEmployeesCreateRequest request);

    AdEmployeesCustomResponse update(@Valid AdEmployeesUpdateRequest request);

    AdEmployeesDetailResponse detail(String id);

    Boolean delete(String id);

    Boolean setEmployeeQuit(String id);

}
