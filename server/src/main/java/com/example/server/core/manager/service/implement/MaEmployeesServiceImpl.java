package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.response.MaEmployeesResponse;
import com.example.server.core.manager.repository.MaEmployeesRepository;
import com.example.server.core.manager.service.MaEmployeesService;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaEmployeesServiceImpl implements MaEmployeesService {

    private final MaEmployeesRepository maEmployeesRepository;

    @Override
    public List<MaEmployeesResponse> getAllEmployeesNotDepartment() {
        return maEmployeesRepository.getAllEmployeesNotDepartment();
    }

    @Override
    @Transactional
    public boolean deleteMissionsAnDepartmentForEmployees(String id) {
        Employees employees = maEmployeesRepository.findById(id)
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        employees.setMissions(new HashSet<>());
        employees.setDepartments(null);
        maEmployeesRepository.save(employees);
        return true;
    }
}
