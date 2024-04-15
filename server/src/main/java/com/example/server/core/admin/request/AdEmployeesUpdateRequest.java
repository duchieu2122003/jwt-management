package com.example.server.core.admin.request;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusEmployee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Địa chỉ cụ thể không được để trống")
    String address;

    @NotBlank(message = "Đường không được để trống")
    String street;

    @NotBlank(message = "Thành phố không được để trống")
    String city;

    @NotBlank(message = "Quốc gia không được để trống")
    String country;

    Role role = Role.STAFF;

    String idDepartments;

    @NotNull(message = "Trạng thái không được để trống")
    StatusEmployee status = StatusEmployee.ACTIVE;

}
