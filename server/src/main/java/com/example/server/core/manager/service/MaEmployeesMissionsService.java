package com.example.server.core.manager.service;

import com.example.server.core.manager.model.request.MaEmployeesMissionsDeleteRequest;
import com.example.server.core.manager.model.request.MaEmployeesMissionsRequest;
import com.example.server.core.manager.model.response.MaEmployeesMissionsCreateResponse;
import com.example.server.core.manager.model.response.MaEmployeesMissionsResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaEmployeesMissionsService {

    List<MaEmployeesMissionsResponse> getAllEmployeesMissions(String idDepartment);

    List<MaEmployeesMissionsCreateResponse> create(List<MaEmployeesMissionsRequest> requests);

    MaEmployeesMissionsResponse update(List<MaEmployeesMissionsRequest> requests);

    boolean delete(MaEmployeesMissionsDeleteRequest request);

}
