package com.example.server.core.common.model.response;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusEmployee;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author duchieu212
 */
public interface CoDetailCustomEmployeeResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.last_name}")
    String getLastName();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.birthday}")
    Date getBirthday();

    @Value("#{target.street}")
    String getStreet();

    @Value("#{target.address}")
    String getAddress();

    @Value("#{target.city}")
    String getCity();

    @Value("#{target.country}")
    String getCountry();

    @Value("#{target.gender}")
    Gender getGender();

    @Value("#{target.status}")
    StatusEmployee getStatus();

    @Value("#{target.role}")
    Role getRole();

    @Value("#{target.name_department}")
    String getNameDepartment();
}
