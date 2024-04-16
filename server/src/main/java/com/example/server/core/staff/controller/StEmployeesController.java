package com.example.server.core.staff.controller;

import com.example.server.core.staff.service.StEmployeesService;
import com.example.server.model.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duchieu212
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staff/employees")
@CrossOrigin
public class StEmployeesController {

    private final StEmployeesService stEmployeesService;

    @GetMapping()
    public ResponseObject viewEmployeesOnDepartmentUser() {
        return new ResponseObject(stEmployeesService.getAllByDepartmentId());
    }
}
