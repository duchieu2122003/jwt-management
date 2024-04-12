package com.example.server.core.common.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author duchieu212
 */
public interface CoDetailCustomEmployeeResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.full_name}")
    String getFullName();

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
    String getGender();
}
