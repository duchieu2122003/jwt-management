package com.example.server.core.common.model.response;

import com.example.server.infrastructure.constant.Role;
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
public class CoEmployeeLoginResponse {

    String id;
    String email;
    String firstName;
    String lastName;
    Role role;
    Date birthday;
    String password;
}
