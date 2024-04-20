package com.example.server.core.manager.controller;

import com.example.server.core.manager.model.request.MaEmployeesMissionsDeleteRequest;
import com.example.server.core.manager.model.request.MaEmployeesMissionsRequest;
import com.example.server.core.manager.model.request.MaEmployeesMissionsUpdateRequest;
import com.example.server.core.manager.service.MaEmployeesMissionsService;
import com.example.server.model.response.ResponseObject;
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

import java.util.List;

/**
 * @author duchieu212
 */
@RestController
@RequestMapping("/api/manager/employees-missions")
@CrossOrigin
@RequiredArgsConstructor
public class MaEmployeesMissionsController {

    private final MaEmployeesMissionsService maEmployeesMissionsService;

    @GetMapping("/{id}")
    public ResponseObject showTableEmployeesMissionInDepartment(@PathVariable("id") String idDepartment) {
        return new ResponseObject(maEmployeesMissionsService.getAllEmployeesMissions(idDepartment));
    }

    @GetMapping("/detail/{id}")
    public ResponseObject detailEmployeesMissionToUpdateByIdEmployees(@PathVariable("id") String idEmployee) {
        return new ResponseObject(maEmployeesMissionsService.getDetailToUpdateByEmployeeId(idEmployee));
    }

    @PostMapping
    public ResponseObject create(@RequestBody List<MaEmployeesMissionsRequest> request) {
        return new ResponseObject(maEmployeesMissionsService.create(request));
    }

    @PutMapping
    public ResponseObject updateEmployeesMissionsByIdEmployees(@RequestBody MaEmployeesMissionsUpdateRequest request) {
        return new ResponseObject(maEmployeesMissionsService.update(request));
    }

    @DeleteMapping("")
    public ResponseObject delete(@RequestBody MaEmployeesMissionsDeleteRequest request) {
        return new ResponseObject(maEmployeesMissionsService.delete(request));
    }
}
