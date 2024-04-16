package com.example.server.core.staff.service.impl;

import com.example.server.core.staff.model.response.StDepartmentsResponse;
import com.example.server.core.staff.repository.StDepartmentRepository;
import com.example.server.core.staff.repository.StEmployeesRepository;
import com.example.server.core.staff.service.StDepartmentsService;
import com.example.server.entity.Departments;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class StDepartmentsServiceImplement implements StDepartmentsService {

    private final StEmployeesRepository stEmployeesRepository;

    @Override
    public StDepartmentsResponse getDepartmentsUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email == null) {
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        }
        Optional<Employees> employees = stEmployeesRepository.findByEmail(email);
        if (employees.isPresent()) {
            Departments departments = employees.get().getDepartments();
            if (departments != null) {
                StDepartmentsResponse result = StDepartmentsResponse.builder()
                        .id(departments.getId())
                        .name(departments.getName())
                        .descriptions(departments.getDescriptions())
                        .status(departments.getStatus())
                        .build();
                return result;
            }
        }
        return null;
    }
}
