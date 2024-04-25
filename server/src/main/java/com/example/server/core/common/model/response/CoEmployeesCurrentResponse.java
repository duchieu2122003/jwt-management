package com.example.server.core.common.model.response;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoEmployeesCurrentResponse {

    String id;

    String code;

    String lastName;

    String firstName;

    String email;

    Date birthday;

    Gender gender;

    String address;

    String street;

    String city;

    String country;

    StatusEmployee status;

    Role role;

    String departmentName;

}
