package com.example.server.core.manager.controller;

import com.example.server.core.manager.model.request.MaDepartmentCreateRequest;
import com.example.server.core.manager.model.request.MaDepartmentUpdateRequest;
import com.example.server.core.manager.service.MaDepartmentService;
import com.example.server.model.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin
@RequestMapping("/api/manager/department")
public class MaDepartmentController {

    private final MaDepartmentService maDepartmentService;

    @GetMapping()
    public ResponseObject getAllDepartment() {
        return new ResponseObject(maDepartmentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseObject getOneDepartment(@PathVariable String id) {
        return new ResponseObject(maDepartmentService.detail(id));
    }

    @PostMapping
    public ResponseObject create(@Valid @RequestBody MaDepartmentCreateRequest request) {
        return new ResponseObject(maDepartmentService.create(request));
    }

    @PutMapping
    public ResponseObject put(@Valid @RequestBody MaDepartmentUpdateRequest request) {
        return new ResponseObject(maDepartmentService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseObject delete(@PathVariable String id) {
        return new ResponseObject(maDepartmentService.delete(id));
    }
}
