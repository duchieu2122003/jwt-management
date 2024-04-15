package com.example.server.core.admin.response;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author duchieu212
 */
public interface AdDepartmentsResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.name}")
    String getName();

}
