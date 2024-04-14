package com.example.server.core.common.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoChangePasswordRequest {

    @NotBlank(message = "Mật khẩu cũ không được để trống")
    String passwordOld;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Mật khẩu phải có ít nhất 6 ký tự gồm chữ và số")
    String passwordNew;
}
