package com.example.server.core.staff.model.response;

import com.example.server.infrastructure.constant.StatusDepartment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StDepartmentsResponse {

    String id;

    String name;

    String descriptions;

    StatusDepartment status;

}
