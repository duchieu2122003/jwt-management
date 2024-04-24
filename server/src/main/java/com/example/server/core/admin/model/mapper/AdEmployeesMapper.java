package com.example.server.core.admin.model.mapper;

import com.example.server.core.admin.model.response.AdEmployeesCustomResponse;
import com.example.server.entity.Employees;
import org.springframework.stereotype.Component;

/**
 * @author duchieu212
 */
@Component
public class AdEmployeesMapper {

    public AdEmployeesCustomResponse employeesToAdEmployeesCustom(Employees e) {
        AdEmployeesCustomResponse response = AdEmployeesCustomResponse.builder()
                .id(e.getId())
                .code(e.getCode())
                .lastName(e.getLastName())
                .firstName(e.getFirstName())
                .birthday(e.getBirthday())
                .gender(e.getGender())
                .departmentId(e.getDepartments()!= null? e.getDepartments().getId():"")
                .departmentName(e.getDepartments() != null ? e.getDepartments().getName() : "")
                .email(e.getEmail())
                .status(e.getStatus())
                .role(e.getRole())
                .address(e.getAddress())
                .street(e.getStreet())
                .city(e.getCity())
                .country(e.getCountry())
                .build();
        return response;
    }
}
