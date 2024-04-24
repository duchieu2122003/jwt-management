package com.example.server.core.admin.model.request;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Họ đệm không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Họ không được quá 255 ký tự")
    String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Tên không được quá 255 ký tự")
    String lastName;

    @NotBlank(message = "Email không được để trống")
    @Size(max = EntityProperties.LENGTH_EMAIL, message = "Email không được quá 50 ký tự")
    String email;

    @NotNull(message = "Ngày sinh không được để trống")
    Date birthday;

    @NotNull(message = "Phải chọn ít nhất 1 giới tính")
    Gender gender;

    @NotBlank(message = "Địa chỉ cụ thể không được để trống")
    String address;

    @NotBlank(message = "Thành phố/Tỉnh không được để trống")
    String city;

    @NotBlank(message = "Tỉnh/Huyện không được để trống")
    String street;

    @NotBlank(message = "Quốc gia không được để trống")
    String country;

    Role role = Role.STAFF;

    String idDepartments;

}
