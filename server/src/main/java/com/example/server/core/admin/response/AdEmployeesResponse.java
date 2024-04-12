package com.example.server.core.admin.response;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author duchieu212
 */
public interface AdEmployeesResponse {

    @Value("#{target.stt}")
    Integer getStt();

    @Value("#{target.fullName}")
    String getFullName();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.birthday}")
    Long getBirthday();

    @Value("#{target.fullAddress}")
    String getFullAddress();

    @Value("#{target.nameDepartment}")
    String getNameDepartment();
}
