package com.example.server.core.manager.model.request;

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
public class MaEmployeesMissionsDeleteRequest {

    String employeesId;

    String missionsId;
}
