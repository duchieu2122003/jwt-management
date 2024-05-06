package com.example.server.core.admin.controller;

import com.example.server.core.admin.model.request.AdDepartmentCreateRequest;
import com.example.server.core.admin.model.request.AdDepartmentUpdateRequest;
import com.example.server.core.admin.service.AdDepartmentsService;
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
@RequestMapping("/api/admin/departments")
@CrossOrigin
public class AdDepartmentsController {

    private final AdDepartmentsService adDepartmentsService;

    @GetMapping()
    public ResponseObject getAllDepartmentActive() {
        return new ResponseObject(adDepartmentsService.getAllDepartmentActive());
    }

    @GetMapping("/get-all")
    public ResponseObject getAllDepartment(){
        return new ResponseObject(adDepartmentsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseObject getOneDepartment(@PathVariable String id) {
        return new ResponseObject(adDepartmentsService.detail(id));
    }

//    @PostMapping
//    public ResponseObject create(@Valid @RequestBody AdDepartmentCreateRequest request) {
//        return new ResponseObject(adDepartmentsService.create(request));
//    }

    @PostMapping
    public ResponseObject create(@RequestBody AdDepartmentCreateRequest request) {
        return new ResponseObject(adDepartmentsService.create(request));
    }

    @PutMapping
    public ResponseObject put(@Valid @RequestBody AdDepartmentUpdateRequest request) {
        return new ResponseObject(adDepartmentsService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseObject delete(@PathVariable("id") String id) {
        return new ResponseObject(adDepartmentsService.delete(id));
    }

}
