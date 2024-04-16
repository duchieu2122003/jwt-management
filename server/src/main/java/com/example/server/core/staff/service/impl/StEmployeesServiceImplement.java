package com.example.server.core.staff.service.impl;

import com.example.server.core.staff.model.response.StEmployeesCustomResponse;
import com.example.server.core.staff.repository.StDepartmentRepository;
import com.example.server.core.staff.repository.StEmployeesRepository;
import com.example.server.core.staff.service.StEmployeesService;
import com.example.server.entity.Departments;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class StEmployeesServiceImplement implements StEmployeesService {

    private final StEmployeesRepository stEmployeesRepository;

    private final StDepartmentRepository stDepartmentRepository;

    @Override
    public List<StEmployeesCustomResponse> getAllByDepartmentId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email == null) {
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        }
        Optional<Employees> employees = stEmployeesRepository.findByEmail(email);
        if (employees.isPresent()) {
            Departments departments = employees.get().getDepartments();
            if (departments != null) {
                return stEmployeesRepository.getAllEmployeesByDepartmentId(departments.getId());
            }
        }
        return null;
    }

}
