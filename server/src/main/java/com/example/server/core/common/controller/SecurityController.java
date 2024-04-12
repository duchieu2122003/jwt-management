package com.example.server.core.common.controller;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.service.CoEmployeeService;
import com.example.server.model.response.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duchieu212
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin("*")
public class SecurityController {

    private final CoEmployeeService coEmployeeService;

    @PostMapping("/login")
    public ResponseObject authenticationEmployee(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) {
        AuthenticationResponse authenticationResponse = coEmployeeService.authenticate(authenticationRequest);
        return new ResponseObject(authenticationResponse);
    }

    @GetMapping("/roles")
    public ResponseObject showRoleMyInfo() {
        return new ResponseObject(coEmployeeService.findMyInfor());
    }

//    @PostMapping("/logout")
//    public ResponseObject logout(HttpServletRequest request) {
//        String authorizationHeader = request.getHeader("Authorization");
//        String token = null;
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7);
//        }
//        new SecurityContextLogoutHandler().logout(request, null, null);
//        return new ResponseObject("Logout ok");
//    }
}
