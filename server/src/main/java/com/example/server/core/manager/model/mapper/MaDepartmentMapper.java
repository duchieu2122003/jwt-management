package com.example.server.core.manager.model.mapper;

import com.example.server.core.manager.model.response.MaDepartmentEmployeesCurrentResponse;
import com.example.server.entity.Departments;
import org.springframework.stereotype.Component;

/**
 * @author duchieu212
 */
@Component
public class MaDepartmentMapper {

    public MaDepartmentEmployeesCurrentResponse departmentToMaDepartmentEmployeesCurrentResponse(Departments d) {
        return MaDepartmentEmployeesCurrentResponse.builder()
                .id(d.getId())
                .name(d.getName())
                .descriptions(d.getDescriptions())
                .status(d.getStatus())
                .build();
    }
}
