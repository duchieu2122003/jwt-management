package com.example.server.core.manager.model.response;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author duchieu212
 */
public interface MaEmployeesResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.full_name}")
    String getFullName();
}
