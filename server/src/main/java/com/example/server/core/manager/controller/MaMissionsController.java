package com.example.server.core.manager.controller;

import com.example.server.core.manager.model.request.MaMissionsCreateRequest;
import com.example.server.core.manager.model.request.MaMissionsUpdateRequest;
import com.example.server.core.manager.service.MaMissionsService;
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
@CrossOrigin
@RequestMapping("/api/manager/missions")
@RequiredArgsConstructor
public class MaMissionsController {

    private final MaMissionsService maMissionsService;

    @GetMapping
    public ResponseObject showAllMissions() {
        return new ResponseObject(maMissionsService.getAll());
    }

    @PostMapping
    public ResponseObject createMission(@Valid @RequestBody MaMissionsCreateRequest request) {
        return new ResponseObject(maMissionsService.create(request));
    }

    @PutMapping
    public ResponseObject updateMission(@Valid @RequestBody MaMissionsUpdateRequest request) {
        return new ResponseObject(maMissionsService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseObject deleteMissions(@PathVariable("id") String id) {
        return new ResponseObject(maMissionsService.deleteMissions(id));
    }

}
