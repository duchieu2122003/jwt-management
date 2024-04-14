package com.example.server.core.manager.service;

import com.example.server.core.manager.model.response.MaMissionsResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaMissionsService {

    List<MaMissionsResponse> getAll();
}
