package com.example.server.core.admin.model.response;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusEmployee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author duchieu212
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdEmployeesCustomResponse {

    String id;

    String code;

    String firstName;

    String lastName;

    String email;

    Date birthday;

    Gender gender;

    String address;

    String street;

    String city;

    String country;

    StatusEmployee status;

    String departmentId;

    String departmentName;

    Role role;

}
