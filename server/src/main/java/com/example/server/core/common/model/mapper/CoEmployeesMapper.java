package com.example.server.core.common.model.mapper;

import com.example.server.core.common.model.response.CoEmployeeLoginResponse;
import com.example.server.core.common.model.response.CoEmployeesCurrentResponse;
import com.example.server.core.common.model.response.CoEmployeesInfoResponse;
import com.example.server.entity.Employees;
import org.springframework.stereotype.Component;

/**
 * @author duchieu212
 */
@Component
public class CoEmployeesMapper {

    public CoEmployeesInfoResponse employeesToCoEmployeesInfoResponse(Employees e) {
        CoEmployeesInfoResponse response = CoEmployeesInfoResponse.builder()
                .id(e.getId())
                .code(e.getCode())
                .lastName(e.getLastName())
                .firstName(e.getFirstName())
                .birthday(e.getBirthday())
                .gender(e.getGender())
                .email(e.getEmail())
                .status(e.getStatus())
                .address(e.getAddress())
                .street(e.getStreet())
                .city(e.getCity())
                .country(e.getCountry())
                .tokenNew(null)
                .build();
        return response;
    }

    public CoEmployeesCurrentResponse employeesToCoEmployeesCurrentResponse(Employees e) {
        CoEmployeesCurrentResponse response = CoEmployeesCurrentResponse.builder()
                .id(e.getId())
                .code(e.getCode())
                .lastName(e.getLastName())
                .firstName(e.getFirstName())
                .birthday(e.getBirthday())
                .gender(e.getGender())
                .email(e.getEmail())
                .status(e.getStatus())
                .address(e.getAddress())
                .street(e.getStreet())
                .city(e.getCity())
                .country(e.getCountry())
                .role(e.getRole())
                .departmentName(e.getDepartments() != null ? e.getDepartments().getName() : "Chưa có phòng ban")
                .build();
        return response;
    }

    public CoEmployeeLoginResponse employeesToCoEmployeeLoginResponse(Employees e) {
        return CoEmployeeLoginResponse.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .password(e.getPassword())
                .birthday(e.getBirthday())
                .role(e.getRole())
                .build();
    }
}
