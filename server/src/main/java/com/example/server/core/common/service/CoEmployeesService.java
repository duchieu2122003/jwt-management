package com.example.server.core.common.service;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.request.CoChangePasswordRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.model.response.CoDetailCustomEmployeeResponse;
import com.example.server.core.common.model.response.CoEmployeesInformationResponse;
import jakarta.validation.Valid;

/**
 * @author duchieu212
 */
public interface CoEmployeesService {

    CoEmployeesInformationResponse findMyInformation();

    AuthenticationResponse authenticate(AuthenticationRequest request);

    CoEmployeesInformationResponse updateEmployeeCurrent(@Valid CoUpdateEmployeeRequest request);

    CoDetailCustomEmployeeResponse detailCustomEmployeeCurrent();

    boolean updatePassword(@Valid CoChangePasswordRequest request);

}
