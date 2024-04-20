package com.example.server.core.manager.service;

import com.example.server.core.manager.model.request.MaMissionsCreateRequest;
import com.example.server.core.manager.model.request.MaMissionsUpdateRequest;
import com.example.server.core.manager.model.response.MaMissionsResponse;
import com.example.server.core.manager.model.response.MaMissionsSaveResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaMissionsService {

    List<MaMissionsResponse> getAll();

    MaMissionsSaveResponse create(MaMissionsCreateRequest request);

    MaMissionsSaveResponse update(MaMissionsUpdateRequest request);

    boolean deleteMissions(String id);
}
