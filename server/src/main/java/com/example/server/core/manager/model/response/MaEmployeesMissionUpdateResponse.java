package com.example.server.core.manager.model.response;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.StatusEmployee;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author duchieu212
 */
public interface MaEmployeesMissionUpdateResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.full_name}")
    String getFullName();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.birthday}")
    Date getBirthday();

    @Value("#{target.gender}")
    Gender getGender();

    @Value("#{target.full_address}")
    String getFullAddress();

    @Value("#{target.status}")
    StatusEmployee getStatus();

    @Value("#{target.full_missions}")
    String getFullMissions();

    @Value("#{target.total_salary}")
    Integer getTotalSalary();
}
