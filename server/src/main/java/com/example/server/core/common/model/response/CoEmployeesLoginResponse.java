package com.example.server.core.common.model.response;

import com.example.server.infrastructure.constant.Role;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author duchieu212
 */
public interface CoEmployeesLoginResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.last_name}")
    String getLastName();

    @Value("#{target.role}")
    Role getRole();

    @Value("#{target.birthday}")
    Date getBirthday();

    @Value("#{target.password}")
    String getPassword();

}
