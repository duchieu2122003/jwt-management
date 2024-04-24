package com.example.server.core.admin.model.response;

import com.example.server.infrastructure.constant.StatusDepartment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdDepartmentsCustomResponse {

    String id;

    String name;

    String descriptions;

    StatusDepartment status;
}
