package com.example.server.core.manager.model.response;

import com.example.server.infrastructure.constant.StatusDepartment;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author duchieu212
 */
public interface MaDepartmentUserCurrentResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.descriptions}")
    String getDescriptions();

    @Value("#{target.status}")
    StatusDepartment getStatus();
}
