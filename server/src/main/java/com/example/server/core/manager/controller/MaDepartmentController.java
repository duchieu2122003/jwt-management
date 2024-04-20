package com.example.server.core.manager.controller;

import com.example.server.core.manager.service.MaDepartmentService;
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
@CrossOrigin
@RequestMapping("/api/manager/departments")
public class MaDepartmentController {

    private final MaDepartmentService maDepartmentService;

    @GetMapping()
    public ResponseObject getAllDepartment() {
        return new ResponseObject(maDepartmentService.getAll());
    }

    @GetMapping("/user-current")
    public ResponseObject getDepartmentByUserCurrent() {
        return new ResponseObject(maDepartmentService.getDepartmentByUserCurrent());
    }
}
