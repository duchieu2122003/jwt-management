package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.mapper.MaEmployeeMapper;
import com.example.server.core.manager.model.response.MaEmployeesResponses;
import com.example.server.core.manager.repository.MaEmployeesRepository;
import com.example.server.core.manager.service.MaEmployeesService;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaEmployeesServiceImpl implements MaEmployeesService {

    private final MaEmployeesRepository maEmployeesRepository;

    private final MaEmployeeMapper maEmployeeMapper;

    @Override
    public List<MaEmployeesResponses> getAllEmployeesNotDepartment() {
        return maEmployeesRepository.getAllEmployeesNotDepartment().stream()
                .map(maEmployeeMapper::employeeToMaEmployeesResponses).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteMissionsAnDepartmentForEmployees(String id) {
        Employees employees = maEmployeesRepository.findById(id)
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null) {
            if (email.equals(employees.getEmail())) {
                throw new RestApiException("Bạn không thể xóa bản thân bạn khỏi phòng ban");
            }
        }

        employees.setMissions(new HashSet<>());
        employees.setDepartments(null);
        maEmployeesRepository.save(employees);
        return true;
    }
}
