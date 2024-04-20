package com.example.server.core.manager.model.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author duchieu212
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaEmployeesMissionDetailResponse {

    String employeesId;

    String code;

    String email;

    String fullName;

    List<String> missionsListId;

}
