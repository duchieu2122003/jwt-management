package com.example.server.core.admin.model.mapper;

import com.example.server.core.admin.model.response.AdDepartmentsCustomResponse;
import com.example.server.entity.Departments;
import org.springframework.stereotype.Component;

/**
 * @author duchieu212
 */
@Component
public class AdDepartmentMapper {

    public AdDepartmentsCustomResponse departmentsToCustomResponse(Departments d) {
        return AdDepartmentsCustomResponse.builder()
                .id(d.getId())
                .name(d.getName())
                .descriptions(d.getDescriptions())
                .status(d.getStatus())
                .build();
    }
}
