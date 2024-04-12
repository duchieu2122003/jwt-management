package com.example.server.core.admin.request;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusEmployee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdEmployeesUpdateRequest {

    String id;

    String firstName;

    String lastName;

    String email;

    Date birthday;

    Gender gender;

    String address;

    String street;

    String city;

    String country;

    Role role = Role.STAFF;

    String idDepartments;

    StatusEmployee status = StatusEmployee.ACTIVE;

}
