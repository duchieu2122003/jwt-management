package com.example.server.core.manager.controller;

import com.example.server.core.manager.service.MaMissionsService;
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
@CrossOrigin
@RequestMapping("/api/manager/missions")
@RequiredArgsConstructor
public class MaMissionsController {

    private final MaMissionsService maMissionsService;

    @GetMapping
    public ResponseObject showAllMissions() {
        return new ResponseObject(maMissionsService.getAll());
    }
}
