package com.example.server.core.common.controller;

import com.example.server.core.common.model.request.CoChangePasswordRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.service.CoEmployeesService;
import com.example.server.model.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duchieu212
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/employees")
@CrossOrigin
public class CoEmployeesController {

    private final CoEmployeesService coEmployeesService;

    @GetMapping()
    public ResponseObject detailEmployeeCustomCurrent() {
        return new ResponseObject(coEmployeesService.detailCustomEmployeeCurrent());
    }

    @PostMapping("")
    public ResponseObject update(@Valid @RequestBody CoUpdateEmployeeRequest request) {
        return new ResponseObject(coEmployeesService.updateEmployeeCurrent(request));
    }

    @PutMapping()
    public ResponseObject changePassword( @RequestBody CoChangePasswordRequest request) {
        return new ResponseObject(coEmployeesService.updatePassword(request));
    }

}
