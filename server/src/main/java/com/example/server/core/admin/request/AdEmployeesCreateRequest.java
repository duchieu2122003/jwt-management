package com.example.server.core.admin.request;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author duchieu212
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdEmployeesCreateRequest {

    @NotBlank(message = "Họ không được để trống")
    String firstName;

    @NotBlank(message = "Tên không được để trống")
    String lastName;

    @NotBlank(message = "Email không được để trống")
    String email;

    @NotNull(message = "Ngày sinh không được để trống")
    Date birthday;

    @NotNull(message = "Giới tính không được để trống")
    Gender gender;

    @NotNull(message = "Địa chỉ cụ thể không được để trống")
    String address;

    @NotNull(message = "Đường không được để trống")
    String street;

    @NotNull(message = "Thành phố không được để trống")
    String city;

    @NotNull(message = "Quốc gia không được để trống")
    String country;

    Role role = Role.STAFF;

    String idDepartments;

}
