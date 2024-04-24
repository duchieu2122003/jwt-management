package com.example.server.core.common.service.implement;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.request.CoChangePasswordRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.model.response.CoDetailCustomEmployeeResponse;
import com.example.server.core.common.model.response.CoEmployeesInformationResponse;
import com.example.server.core.common.model.response.CoEmployeesLoginResponse;
import com.example.server.core.common.repository.CoEmployeesRepository;
import com.example.server.core.common.service.CoEmployeesService;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.infrastructure.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CoEmployeesServiceImpl implements CoEmployeesService {

    private final CoEmployeesRepository coEmployeesRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        CoEmployeesLoginResponse employee = coEmployeesRepository.findEmployeesByEmailToLogin(
                request.getEmail()).orElseThrow(() -> new RestApiException(Message.EMAIL_NOT_EXIST));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), employee.getPassword());
        if (!authenticated) {
            throw new RestApiException(Message.PASSWORD_WRONG);
        }
        String token = jwtTokenProvider.generateToken(employee);
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(employee.getRole().name());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
                (employee, null, Collections.singletonList(authorities));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    @Transactional
    public CoEmployeesInformationResponse updateEmployeeCurrent(CoUpdateEmployeeRequest request) {
        Employees employee = coEmployeesRepository.findById(request.getId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        Employees findEmployees = coEmployeesRepository.findEmployeesByEmail(request.getEmail())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        if (request.getBirthday().after(new Date())) {
            throw new RestApiException(Message.BIRTHDAY_AFTER_NOW);
        }
        if (findEmployees != null && !findEmployees.getId().equals(request.getId())) {
            throw new RestApiException(Message.EMAIL_EXSITS);
        }
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setGender(request.getGender());
        employee.setBirthday(request.getBirthday());
        employee.setAddress(request.getAddress());
        employee.setStreet(request.getStreet());
        employee.setCity(request.getCity());
        employee.setCountry(request.getCountry());
        Employees employeeSave = coEmployeesRepository.save(employee);
        return coEmployeesRepository.findEmployeesMyAuth(employeeSave.getEmail()).orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
    }

    @Override
    public CoDetailCustomEmployeeResponse detailCustomEmployeeCurrent() {
        Authentication email = SecurityContextHolder.getContext().getAuthentication();
        Optional<CoDetailCustomEmployeeResponse> employee = coEmployeesRepository
                .findEmployeeCustomByEmail(email.getName());
        if (employee.isEmpty())
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return employee.get();
    }

    @Override
    public boolean updatePassword(CoChangePasswordRequest request) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employees employees = coEmployeesRepository.findEmployeesByEmail(email)
                .orElseThrow(() -> new RestApiException(Message.LOGIN_FAILD));
        if (passwordEncoder.matches(request.getPasswordOld(), employees.getPassword())) {
            if (request.getPasswordNew().matches(regex)) {
                employees.setPassword(passwordEncoder.encode(request.getPasswordNew()));
            } else {
                throw new RestApiException(Message.PASSWORD_NEW_WRONG_FORMAT);
            }
            employees.setPassword(passwordEncoder.encode(request.getPasswordNew()));
        } else {
            throw new RestApiException(Message.PASSWORD_OLD_WRONG);
        }
        coEmployeesRepository.save(employees);
        return true;
    }
}
