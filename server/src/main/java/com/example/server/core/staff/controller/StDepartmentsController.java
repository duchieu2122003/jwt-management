package com.example.server.core.staff.controller;

import com.example.server.core.staff.service.StDepartmentsService;
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
@RequestMapping("/api/staff/departments")
@CrossOrigin
public class StDepartmentsController {

    private final StDepartmentsService stDepartmentsService;

    @GetMapping()
    public ResponseObject viewDepartmentsUser() {
        return new ResponseObject(stDepartmentsService.getDepartmentsUser());
    }
}
