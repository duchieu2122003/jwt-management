package com.example.server.core.common.service.implement;

import com.example.server.core.common.model.mapper.CoEmployeesMapper;
import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.request.CoChangePasswordRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.model.response.AuthenticationHeaderResponse;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.model.response.CoEmployeeLoginResponse;
import com.example.server.core.common.model.response.CoEmployeesCurrentResponse;
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
public class CoEmployeesServiceImpl implements CoEmployeesService {

    private final CoEmployeesRepository coEmployeesRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final CoEmployeesMapper coEmployeesMapper;

    @Override
    public boolean logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.clearContext();
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        CoEmployeeLoginResponse employee = coEmployeesMapper.employeesToCoEmployeeLoginResponse(
                coEmployeesRepository.findEmployeesByEmailToLogin(
                        request.getEmail()).orElseThrow(() -> new RestApiException(Message.EMAIL_NOT_EXIST)));
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
                .id(employee.getId())
                .lastName(employee.getLastName())
                .role(employee.getRole())
                .build();
    }

    @Override
    @Transactional
    public AuthenticationResponse updateEmployeeCurrent(CoUpdateEmployeeRequest request) {
        Employees employeesId = coEmployeesRepository.findById(request.getId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        Optional<Employees> findEmployeesEmail = coEmployeesRepository.findEmployeesByEmail(request.getEmail());
        if (request.getBirthday().after(new Date())) {
            throw new RestApiException(Message.BIRTHDAY_AFTER_NOW);
        }
        if (findEmployeesEmail.isPresent() && !findEmployeesEmail.get().getId().equals(request.getId())) {
            throw new RestApiException(Message.EMAIL_EXSITS);
        }
        employeesId.setFirstName(request.getFirstName());
        employeesId.setLastName(request.getLastName());
        employeesId.setEmail(request.getEmail());
        employeesId.setGender(request.getGender());
        employeesId.setBirthday(request.getBirthday());
        employeesId.setAddress(request.getAddress());
        employeesId.setStreet(request.getStreet());
        employeesId.setCity(request.getCity());
        employeesId.setCountry(request.getCountry());
        Employees employeeSave = coEmployeesRepository.save(employeesId);
        return AuthenticationResponse.builder()
                .id(employeeSave.getId())
                .lastName(employeeSave.getLastName())
                .role(employeeSave.getRole())
                .token(jwtTokenProvider.generateToken(coEmployeesMapper
                        .employeesToCoEmployeeLoginResponse(employeeSave)))
                .build();
    }

    @Override
    public AuthenticationHeaderResponse detailEmployeesCurrentForHeader() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Employees> employee = coEmployeesRepository
                .findEmployeesMyAuth(email);
        if (employee.isEmpty())
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return AuthenticationHeaderResponse.builder()
                .id(employee.get().getId())
                .role(employee.get().getRole())
                .lastName(employee.get().getLastName())
                .build();
    }

    @Override
    public CoEmployeesCurrentResponse detailCustomEmployeeCurrent() {
        Authentication email = SecurityContextHolder.getContext().getAuthentication();
        Optional<Employees> employee = coEmployeesRepository
                .findEmployeesMyAuth(email.getName());
        if (employee.isEmpty())
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return coEmployeesMapper.employeesToCoEmployeesCurrentResponse(employee.get());
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
