package com.example.server.core.manager.service;

import com.example.server.core.manager.model.request.MaMissionsCreateRequest;
import com.example.server.core.manager.model.request.MaMissionsUpdateRequest;
import com.example.server.core.manager.model.response.MaMissionResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaMissionsService {

    List<MaMissionResponse> getAll();

    MaMissionResponse create(MaMissionsCreateRequest request);

    MaMissionResponse update(MaMissionsUpdateRequest request);

    boolean deleteMissions(String id);
}
