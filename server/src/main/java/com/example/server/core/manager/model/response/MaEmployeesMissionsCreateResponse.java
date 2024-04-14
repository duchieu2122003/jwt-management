package com.example.server.core.manager.model.response;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.StatusEmployee;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author duchieu212
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaEmployeesMissionsCreateResponse {

    String id;

    String code;

    String fullName;

    String email;

    Date birthday;

    Gender gender;

    String fullAddress;

    StatusEmployee status;

    String fullMissions;
}

