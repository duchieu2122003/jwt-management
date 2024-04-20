package com.example.server.core.manager.service;

import com.example.server.core.manager.model.response.MaDepartmentResponse;
import com.example.server.core.manager.model.response.MaDepartmentUserCurrentResponse;

import java.util.List;

/**
 * @author duchieu212
 */
public interface MaDepartmentService {

    List<MaDepartmentResponse> getAll();

    MaDepartmentUserCurrentResponse getDepartmentByUserCurrent();

}
