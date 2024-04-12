package com.example.server.core.common.service.implement;

import com.example.server.core.common.model.request.AuthenticationRequest;
import com.example.server.core.common.model.request.CoUpdateEmployeeRequest;
import com.example.server.core.common.model.response.AuthenticationResponse;
import com.example.server.core.common.model.response.CoDetailCustomEmployeeResponse;
import com.example.server.core.common.model.response.CoDetailEmployeeResponse;
import com.example.server.core.common.repository.CoEmployeeRepository;
import com.example.server.core.common.service.CoEmployeeService;
import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.infrastructure.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CoEmployeeImpl implements CoEmployeeService {

    private final CoEmployeeRepository coEmployeeRepository;

    @NonFinal
    @Value("${identity.secretKey}")
    private String SECRET_TOKEN;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public Employees findMyInfor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return coEmployeeRepository.findByEmail(email).orElseThrow(() -> new RestApiException("Không tìm thấy tài khoản đang đăng nhập"));
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Employees employee = coEmployeeRepository.findByEmail(
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
    public CoDetailEmployeeResponse updateEmployeeCurrent(CoUpdateEmployeeRequest request) {
        Employees employee = coEmployeeRepository.findById(request.getId()).orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        String fullName = request.getFullName();
        String firstName = "";
        String lastName = "";
        int indexLastSpace = fullName.lastIndexOf(" ");
        if (indexLastSpace != -1) {
            firstName = fullName.substring(0, indexLastSpace);
            lastName = fullName.substring(indexLastSpace + 1);
        }
        employee = Employees.builder()
                .lastName(lastName)
                .firstName(firstName)
                .email(request.getEmail())
                .gender(request.getGender() == "MALE" ? Gender.MALE : Gender.FEMALE)
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .birthday(request.getBirthday())
                .build();
        Employees employeeSave = coEmployeeRepository.save(employee);
        if (employeeSave == null) {
            throw new RestApiException(Message.EMPLOYEE_NOT_SAVE);
        }
        return CoDetailEmployeeResponse.builder()
                .id(employeeSave.getId())
                .fullName(employeeSave.getFirstName() + " " + employee.getLastName())
                .email(employeeSave.getEmail())
                .address(employeeSave.getAddress())
                .city(employeeSave.getCity())
                .country(employeeSave.getCountry())
                .code(employeeSave.getCode())
                .birthday(employeeSave.getBirthday())
                .build();
    }

    @Override
    public CoDetailCustomEmployeeResponse detailCustomEmployeeCurrent() {
        var email = SecurityContextHolder.getContext().getAuthentication();
        Optional<CoDetailCustomEmployeeResponse> employee = coEmployeeRepository
                .findEmployeeCustomByEmail(email.getName());
        if (!employee.isPresent())
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        return employee.get();
    }
}
