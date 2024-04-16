package com.example.server.core.admin.service.implement;

import com.example.server.core.admin.model.request.AdEmployeesCreateRequest;
import com.example.server.core.admin.model.request.AdEmployeesCustomRequest;
import com.example.server.core.admin.model.request.AdEmployeesUpdateRequest;
import com.example.server.core.admin.model.response.AdEmployeesCustomResponse;
import com.example.server.core.admin.model.response.AdEmployeesDetailResponse;
import com.example.server.core.admin.repository.AdDepartmentsRepository;
import com.example.server.core.admin.repository.AdEmployeesRepository;
import com.example.server.core.admin.service.AdEmployeesService;
import com.example.server.entity.Departments;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.constant.StatusEmployee;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.util.EmployeesHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class AdEmployeesServiceImpl implements AdEmployeesService {

    private AdEmployeesRepository adEmployeesRepository;

    private AdDepartmentsRepository adDepartmentsRepository;

    @Autowired
    public AdEmployeesServiceImpl(AdEmployeesRepository adEmployeesRepository, AdDepartmentsRepository adDepartmentsRepository) {
        this.adEmployeesRepository = adEmployeesRepository;
        this.adDepartmentsRepository = adDepartmentsRepository;
    }

    @Override
    public Page<AdEmployeesCustomResponse> getAdPageEmployeeCustom(final AdEmployeesCustomRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<AdEmployeesCustomResponse> page = adEmployeesRepository.getAdPageEmployeeCustom(request, pageable);
        return page;
    }


    @Override
    @Transactional
    public AdEmployeesCustomResponse create(AdEmployeesCreateRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Departments departments = null;
        if (request.getBirthday().after(new Date())) {
            throw new RestApiException(Message.BIRTHDAY_AFTER_NOW);
        }
        if (!request.getIdDepartments().equals("")) {
            Departments departmentsOptional = adDepartmentsRepository
                    .findById(request.getIdDepartments()).orElseThrow(()
                            -> new RestApiException(Message.DEPARTMENT_NOT_EXSIST));
            departments = departmentsOptional;
        }
        Optional<Employees> findEmployees = adEmployeesRepository.findEmployeesByEmail(request.getEmail());
        if (findEmployees.isPresent()) {
            throw new RestApiException(Message.EMAIL_EXSITS);
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
        String id = employeesSave.getId();
        AdEmployeesCustomResponse adEmployeesCustomResponse = adEmployeesRepository.findEmployeesCustomById(id);
        if (adEmployeesCustomResponse == null) throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return adEmployeesCustomResponse;
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
        Departments departments = null;
        if (request.getIdDepartments() != "") {
            Departments departmentsOptional = adDepartmentsRepository
                    .findById(request.getIdDepartments()).orElseThrow(()
                            -> new RestApiException(Message.DEPARTMENT_NOT_EXSIST));
            departments = departmentsOptional;
        }

        Employees employeesSave = Employees.builder()
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
                .birthday(request.getBirthday())
                .status(request.getStatus())
                .departments(departments)
                .build();
        adEmployeesRepository.save(employeesSave);
        AdEmployeesCustomResponse adEmployeesCustomResponse = adEmployeesRepository.findEmployeesCustomById(request.getId());
        if (adEmployeesCustomResponse == null) throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return adEmployeesCustomResponse;
    }

    @Override
    public AdEmployeesDetailResponse detail(String id) {
        return adEmployeesRepository.findEmployeesDetailById(id);
    }

    @Override
    @Transactional
    public Boolean delete(String id) {
        Employees employees = adEmployeesRepository.findById(id).orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        employees.setDepartments(null);
        adEmployeesRepository.delete(employees);
        return true;
    }

    @Override
    @Transactional
    public Boolean setEmployeeQuit(String id) {
        Employees employees = adEmployeesRepository.findById(id).orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        employees.setStatus(StatusEmployee.INACTIVE);
        return true;

    }

}
