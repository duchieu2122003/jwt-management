package com.example.server.core.manager.controller;

import com.example.server.core.manager.service.MaEmployeesService;
import com.example.server.model.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duchieu212
 */
@RestController
@RequestMapping("/api/manager/employees")
@RequiredArgsConstructor
@CrossOrigin
public class MaEmployeesController {

    private final MaEmployeesService maEmployeesService;

    @GetMapping("/not-departments")
    public ResponseObject showEmployeesNotDepartments() {
        return new ResponseObject(maEmployeesService.getAllEmployeesNotDepartment());
    }

    @DeleteMapping("/delete-department/{employeeId}")
    public ResponseObject setNullDepartmentForEmployees(@PathVariable("employeeId") String employeeId) {
        return new ResponseObject(maEmployeesService.deleteMissionsAnDepartmentForEmployees(employeeId));
    }


}
