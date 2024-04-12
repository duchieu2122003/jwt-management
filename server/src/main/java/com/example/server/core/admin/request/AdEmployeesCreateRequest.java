package com.example.server.core.admin.request;

import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
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
//    //    @NotBlank(message = "NAME_NOT_EMPTY")
////    @Size(max = EntityProperties.LENGTH_NAME, message = "NAME_INVALID")
//    String firstName;
//    //    @NotBlank(message = "LAST_NAME_NOT_EMPTY")
////    @Size(max = EntityProperties.LENGTH_NAME, message = "NAME_INVALID")
//    String lastName;
//    //    @NotBlank(message = "EMAIL_DUPLICATION")
////    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "EMAIL_INVALID")
////    @Size(max = EntityProperties.LENGTH_EMAIL, message = "EMAIL_INVALID")
////    @UniqueElements(message = "EMAIL_DUPLICATION")
//    String email;
//    //    @NotNull(message = "BIRTHDAY_NOT_NULL")
//    Long birthday;
//    //    @NotBlank(message = "ADDRESS_NOT_EMPTY")
////    @Size(max = EntityProperties.LENGTH_DESCRIPTIONS_SHORT, message = "ADDRESS_INVALID")
//    String address;
//
//    //    @NotBlank(message = "ADDRESS_NOT_EMPTY")
////    @Size(max = EntityProperties.LENGTH_DESCRIPTIONS_SHORT, message = "ADDRESS_INVALID")
//    String city;
//
//    String country;
//
//    List<String> roles;
//
//    String idDepartment;
//

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

}
