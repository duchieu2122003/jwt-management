package com.example.server.infrastructure.constant;

import lombok.Getter;

/**
 * @author duchieu212
 */
@Getter
public enum Message {

    ERROR_UNKNOWN("Lỗi không xác định"),
    UNCATEGORIZED_EXCEPTION("Lỗi chưa được phân loại"),
    NAME_INVALID("Tên không được quá {min} kí tự"),
    NAME_DUPLICATION("Tên đã tồn tại"),
    DESCRIPTIONS_INVALID("Mô tả không được quá {max} kí tự"),
    LAST_NAME_NOT_EMPTY("Tên đệm không được để trống"),
    EMAIL_NOT_EMPTY("Email không được để trống"),
    EMAIL_INVALID("Email phải có định dạng @gmail.com và nhiều nhất {max} kí tự"),
    EMAIL_DUPLICATION("Email đã tồn tại"),
    BIRTHDAY_NOT_NULL("Ngày sinh không được để trống"),
    ADDRESS_NOT_EMPTY("Địa chỉ không được để trống"),
    ADDRESS_INVALID("Địa chỉ không được quá {max} kí tự"),
    EMAIL_NOT_EXIST("Email không tồn tại"),
    PASSWORD_WRONG("Mật khẩu sai"),
    UNAUTHENTICATED_LOGIN("Lỗi xác thực, vui lòng đăng nhập để thực hiện chức năng"),
    EMPLOYEE_NOT_EXIST("Tài khoản không tồn tại"),
    EMPLOYEE_NOT_SAVE("Hệ thống lỗi, không thể cập nhật, vui lòng thử lại"),
    DEPARTMENT_NOT_EXSIST("Phòng ban không tồn tại"),
    DEPARTMENT_NAME_EXSIST("Tên phòng ban đã tồn tại"),
    EMAIL_EXSITS("Email đã tồn tại, vui lòng dùng email khác"),
    LOGIN_FAILD("Không tìm thấy tài khoản đang đăng nhập, vui lòng đăng nhập lại"),
    NAME_NOT_EMPTY("Tên không được để trống");


    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
