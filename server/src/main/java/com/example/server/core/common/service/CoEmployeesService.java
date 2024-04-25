package com.example.server.core.common.service;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.request.CoChangePasswordRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.model.response.AuthenticationHeaderResponse;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.model.response.CoEmployeesCurrentResponse;
import jakarta.validation.Valid;

/**
 * @author duchieu212
 */
public interface CoEmployeesService {

    boolean logout();

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse updateEmployeeCurrent(@Valid CoUpdateEmployeeRequest request);

    AuthenticationHeaderResponse detailEmployeesCurrentForHeader();

    CoEmployeesCurrentResponse detailCustomEmployeeCurrent();

    boolean updatePassword(@Valid CoChangePasswordRequest request);

}
