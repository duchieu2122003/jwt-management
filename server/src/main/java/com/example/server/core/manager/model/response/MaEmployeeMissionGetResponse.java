package com.example.server.core.manager.model.response;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author duchieu212
 */
public interface MaEmployeeMissionGetResponse {

    @Value("#{target.employee_id}")
    String getEmployeeId();

    @Value("#{target.mission_id}")
    String getMissionId();

}
