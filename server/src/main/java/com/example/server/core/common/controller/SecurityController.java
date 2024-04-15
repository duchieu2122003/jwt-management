package com.example.server.core.common.controller;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.service.CoEmployeesService;
import com.example.server.model.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duchieu212
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
@CrossOrigin
public class SecurityController {

    private final CoEmployeesService coEmployeesService;

    @PostMapping("/login")
    public ResponseObject authenticationEmployee(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = coEmployeesService.authenticate(authenticationRequest);
        return new ResponseObject(authenticationResponse);
    }

    @GetMapping("/roles")
    public ResponseObject showRoleMyInfo() {
        return new ResponseObject(coEmployeesService.findMyInformation());
    }

}
