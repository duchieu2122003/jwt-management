package com.example.server.core.common.service;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.model.response.CoDetailCustomEmployeeResponse;
import com.example.server.core.common.model.response.CoDetailEmployeeResponse;
import com.example.server.entity.Employees;

/**
 * @author duchieu212
 */
public interface CoEmployeeService {

    Employees findMyInfor();

    AuthenticationResponse authenticate(AuthenticationRequest request);

    CoDetailEmployeeResponse updateEmployeeCurrent(CoUpdateEmployeeRequest request);

    CoDetailCustomEmployeeResponse detailCustomEmployeeCurrent();

}
