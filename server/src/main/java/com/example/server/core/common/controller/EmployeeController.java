package com.example.server.core.common.controller;

import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.service.CoEmployeeService;
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
@RequestMapping("/api/common/employee")
@CrossOrigin("*")
public class EmployeeController {

    private final CoEmployeeService coEmployeeService;

    @GetMapping("/detail")
    public ResponseObject detailEmployeeCustomCurrent() {
        return new ResponseObject(coEmployeeService.detailCustomEmployeeCurrent());
    }

    @PostMapping("/update")
    public ResponseObject update(@RequestBody CoUpdateEmployeeRequest request) {
        return new ResponseObject(coEmployeeService.updateEmployeeCurrent(request));
    }

}
