package com.example.server.core.common.model.request;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.Gender;
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
public class CoUpdateEmployeeRequest {

    String id;

    @NotBlank(message = "Tên đệm không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Tên không được quá 50 ký tự")
    String lastName;

    @NotBlank(message = "Họ không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Họ không được quá 50 ký tự")
    String firstName;

    @NotBlank(message = "Email không được để trống")
    @Size(max = EntityProperties.LENGTH_EMAIL, message = "Email không được quá 50 ký tự")
    String email;

    @NotNull(message = "Ngày sinh không được để trống")
    Date birthday;

    @NotNull(message = "Giới tính không được để trống")
    Gender gender;

    @NotBlank(message = "Địa chỉ cụ thể không được để trống")
    String address;

    @NotBlank(message = "Đường cụ thể không được để trống")
    String street;

    @NotBlank(message = "Thành phố không được để trống")
    String city;

    @NotBlank(message = "Quốc gia không được để trống")
    String country;

}
