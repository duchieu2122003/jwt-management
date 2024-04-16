package com.example.server.core.admin.controller;

import com.example.server.core.admin.service.AdDepartmentsService;
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
@RequestMapping("/api/admin/departments")
@CrossOrigin
public class AdDepartmentsController {

    private final AdDepartmentsService adDepartmentsService;

    @GetMapping()
    public ResponseObject getAllDepartmentActive() {
        return new ResponseObject(adDepartmentsService.getAllDepartmentActive());
    }

}
