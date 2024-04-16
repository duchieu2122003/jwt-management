package com.example.server.core.manager.model.response;

import com.example.server.infrastructure.constant.StatusDepartment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaDepartmentResponse {

    Integer stt;

    String id;

    String name;

    String descriptions;

    StatusDepartment status;
}
