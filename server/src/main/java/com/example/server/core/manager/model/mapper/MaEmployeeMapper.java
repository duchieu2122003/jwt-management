package com.example.server.core.manager.model.mapper;

import com.example.server.core.manager.model.response.MaEmployeesResponses;
import com.example.server.entity.Employees;
import org.springframework.stereotype.Component;

/**
 * @author duchieu212
 */
@Component
public class MaEmployeeMapper {

    public MaEmployeesResponses employeeToMaEmployeesResponses(Employees e) {
        return MaEmployeesResponses.builder()
                .id(e.getId())
                .email(e.getEmail())
                .fullName(e.getFirstName() + " " + e.getLastName())
                .code(e.getCode())
                .build();
    }
}
