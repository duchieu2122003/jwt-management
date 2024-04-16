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
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CoEmployeesImpl implements CoEmployeesService {

    private final CoEmployeesRepository coEmployeesRepository;

    @NonFinal
    @Value("${identity.secretKey}")
    private String SECRET_TOKEN;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public CoEmployeesInformationResponse findMyInformation() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<CoEmployeesInformationResponse> employees = Optional.ofNullable(coEmployeesRepository.findEmployeesMyAuth(email)
                .orElseThrow(() -> new RestApiException(Message.LOGIN_FAILD)));
        return employees.get();
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
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(employee, null, Collections.singletonList(authorities));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    @Transactional
    public CoEmployeesInformationResponse updateEmployeeCurrent(CoUpdateEmployeeRequest request) {
        Employees employee = coEmployeesRepository.findById(request.getId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        System.err.println("aaaaaaaaaaaaaaaaaaa");
        Optional<Employees> findEmployees = coEmployeesRepository.findEmployeesByEmail(request.getEmail());
        System.err.println("bbbbbbbbbbbbbbbbbbb");
        if (request.getBirthday().after(new Date())) {
            throw new RestApiException(Message.BIRTHDAY_AFTER_NOW);
        }
        if (findEmployees.isPresent() && !findEmployees.get().getId().equals(request.getId())) {
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
        if (employeeSave == null) {
            throw new RestApiException(Message.EMPLOYEE_NOT_SAVE);
        }
        System.err.println("cccccccccccc");
        return coEmployeesRepository.findEmployeesMyAuth(request.getEmail()).get();
    }

    @Override
    public CoDetailCustomEmployeeResponse detailCustomEmployeeCurrent() {
        Authentication email = SecurityContextHolder.getContext().getAuthentication();
        Optional<CoDetailCustomEmployeeResponse> employee = coEmployeesRepository
                .findEmployeeCustomByEmail(email.getName());
        if (!employee.isPresent())
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return employee.get();
    }

    @Override
    public boolean updatePassword(CoChangePasswordRequest request) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Employees> employees = Optional.ofNullable(coEmployeesRepository.findEmployeesByEmail(email)
                .orElseThrow(() -> new RestApiException(Message.LOGIN_FAILD)));
        if (passwordEncoder.matches(request.getPasswordOld(), employees.get().getPassword())) {
            if (request.getPasswordNew().matches(regex)) {
                employees.get().setPassword(passwordEncoder.encode(request.getPasswordNew()));
            } else {
                throw new RestApiException(Message.PASSWORD_NEW_WRONG_FORMAT);
            }
            employees.get().setPassword(passwordEncoder.encode(request.getPasswordNew()));
        } else {
            throw new RestApiException(Message.PASSWORD_OLD_WRONG);
        }
        coEmployeesRepository.save(employees.get());
        return true;
    }
}
