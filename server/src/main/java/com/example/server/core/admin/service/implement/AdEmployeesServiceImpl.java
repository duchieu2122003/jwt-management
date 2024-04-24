package com.example.server.core.admin.service.implement;

import com.example.server.core.admin.model.mapper.AdEmployeesMapper;
import com.example.server.core.admin.model.request.AdEmployeesCreateRequest;
import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.model.request.AdEmployeesUpdateRequest;
import com.example.server.core.admin.model.response.AdEmployeesCustomResponse;
import com.example.server.core.admin.repository.AdDepartmentsRepository;
import com.example.server.core.admin.repository.AdEmployeesRepository;
import com.example.server.core.admin.service.AdEmployeesService;
import com.example.server.entity.Departments;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusEmployee;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.util.EmployeesHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class AdEmployeesServiceImpl implements AdEmployeesService {

    private final AdEmployeesRepository adEmployeesRepository;

    private final AdDepartmentsRepository adDepartmentsRepository;

    private final AdEmployeesMapper adEmployeesMapper;

    @Override
    public Page<AdEmployeesCustomResponse> getAdPageEmployeeCustom(final AdEmployeesCustomRequest request) {
        if (!request.getName().equals("")) {
            request.setName(request.getName().trim());
        }
        if (!request.getEmail().equals("")) {
            request.setEmail(request.getEmail().trim());
        }
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Employees> pageEmployees = adEmployeesRepository.getAdPageEmployeeCustom(request, pageable);
        return pageEmployees.map(e -> adEmployeesMapper.employeesToAdEmployeesCustom(e));
    }

    @Override
    @Transactional
    public AdEmployeesCustomResponse create(AdEmployeesCreateRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Departments departments = null;
        if (request.getBirthday().after(new Date())) {
            throw new RestApiException(Message.BIRTHDAY_AFTER_NOW);
        }
        Optional<Employees> findEmployees = adEmployeesRepository.findEmployeesByEmail(request.getEmail());
        if (findEmployees.isPresent()) {
            throw new RestApiException(Message.EMAIL_EXSITS);
        }
        if (!request.getIdDepartments().equals("")) {
            departments = adDepartmentsRepository
                    .findById(request.getIdDepartments()).orElseThrow(()
                            -> new RestApiException(Message.DEPARTMENT_NOT_EXSIST));
            if (request.getRole().equals(Role.MANAGER)) {
                Integer countManager = adEmployeesRepository.countManagerInDepartment(request.getIdDepartments());
                if (countManager >= 1) {
                    throw new RestApiException(Message.DEPARTMENT_HAD_MANAGER);
                }
            }
        }
        if (request.getRole().equals(Role.ADMIN)) {
            Integer countAdmin = adEmployeesRepository.countAdminSystem();
            if (countAdmin >= 1) {
                throw new RestApiException(Message.SYSTEM_HAVE_ADMIN);
            }
        }
        Employees employees = Employees.builder()
                .code(EmployeesHelper.generateEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .email(request.getEmail())
                .address(request.getAddress())
                .street(request.getStreet())
                .city(request.getCity())
                .country(request.getCountry())
                .role(request.getRole())
                .birthday(request.getBirthday())
                .password(passwordEncoder.encode("1"))
                .status(StatusEmployee.ACTIVE)
                .departments(departments)
                .build();
        Employees employeesSave = adEmployeesRepository.save(employees);
        return adEmployeesMapper.employeesToAdEmployeesCustom(adEmployeesRepository.findEmployeesCustomById(employeesSave.getId()));
    }

    @Override
    @Transactional
    public AdEmployeesCustomResponse update(AdEmployeesUpdateRequest request) {
        Employees employees = adEmployeesRepository.findById(request.getId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        Optional<Employees> findEmployees = adEmployeesRepository.findEmployeesByEmail(request.getEmail());
        if (request.getBirthday().after(new Date())) {
            throw new RestApiException(Message.BIRTHDAY_AFTER_NOW);
        }
        if (findEmployees.isPresent() && !findEmployees.get().getId().equals(request.getId())) {
            throw new RestApiException(Message.EMAIL_EXSITS);
        }
        if (request.getRole().equals(Role.MANAGER)) {
            Integer countManager = adEmployeesRepository.countManagerInDepartment(request.getIdDepartments());
            if (!employees.getRole().equals(Role.MANAGER) && countManager >= 1) {
                throw new RestApiException(Message.DEPARTMENT_HAD_MANAGER);
            }
        }
        Departments departments = null;
        if (!request.getIdDepartments().equals("")) {
            departments = adDepartmentsRepository
                    .findById(request.getIdDepartments()).orElseThrow(()
                            -> new RestApiException(Message.DEPARTMENT_NOT_EXSIST));
        }
        Employees employeesMeger = Employees.builder()
                .id(employees.getId())
                .code(employees.getCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .email(request.getEmail())
                .address(request.getAddress())
                .street(request.getStreet())
                .city(request.getCity())
                .country(request.getCountry())
                .role(request.getRole())
                .password(employees.getPassword())
                .birthday(request.getBirthday())
                .status(request.getStatus())
                .departments(departments)
                .build();
        if (request.getIdDepartments().equals("")) {
            employeesMeger.setMissions(new HashSet<>());
        }
        Employees employeesSave = adEmployeesRepository.save(employeesMeger);
        return adEmployeesMapper.employeesToAdEmployeesCustom(adEmployeesRepository.findEmployeesCustomById(employeesSave.getId()));

    }

    @Override
    public AdEmployeesCustomResponse detail(String id) {
        return adEmployeesMapper.employeesToAdEmployeesCustom(adEmployeesRepository.findEmployeesDetailById(id));
    }

    @Override
    @Transactional
    public Boolean delete(String id) {
        adEmployeesRepository.findById(id).orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        adEmployeesRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Boolean setEmployeeQuit(String id) {
        Employees employees = adEmployeesRepository.findById(id).orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        employees.setStatus(StatusEmployee.INACTIVE);
        return true;

    }

}
