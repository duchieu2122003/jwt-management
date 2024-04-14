package com.example.server.core.admin.controller;

import com.example.server.core.admin.model.request.AdEmployeesCreateRequest;
import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.model.request.AdEmployeesUpdateRequest;
import com.example.server.core.admin.service.AdEmployeesService;
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
@RequestMapping("/api/admin/employees")
@CrossOrigin(origins = "{*}")
public class AdEmployeesController {

    private final AdEmployeesService adEmployeesService;

    @GetMapping()
    public ResponseObject showPageEmployeeManagement(final AdEmployeesCustomRequest request) {
        return new ResponseObject(adEmployeesService.getAdPageEmployeeCustom(request));
    }

    @GetMapping("/{id}")
    public ResponseObject getOne(@PathVariable String id) {
        return new ResponseObject(adEmployeesService.detail(id));
    }

    @PostMapping
    public ResponseObject create(@Valid @RequestBody AdEmployeesCreateRequest request) {
        return new ResponseObject(adEmployeesService.create(request));
    }

    @PutMapping
    public ResponseObject update(@Valid @RequestBody AdEmployeesUpdateRequest request) {
        return new ResponseObject(adEmployeesService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseObject delete(@PathVariable String id) {
        return new ResponseObject(adEmployeesService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseObject update(@PathVariable String id) {
        return new ResponseObject(adEmployeesService.setEmployeeQuit(id));
    }
}
