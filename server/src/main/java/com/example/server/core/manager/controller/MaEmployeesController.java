package com.example.server.core.manager.controller;

import com.example.server.model.response.ResponseObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duchieu212
 */
@RestController
@RequestMapping("/api/manager/employees")
@CrossOrigin
public class MaEmployeesController {

    @GetMapping
    public ResponseObject te(){
        return new ResponseObject("aaaaaaaaaa");
    }


}
