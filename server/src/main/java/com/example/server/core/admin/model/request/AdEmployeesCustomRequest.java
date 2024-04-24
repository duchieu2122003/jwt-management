package com.example.server.core.admin.model.request;

import com.example.server.model.request.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdEmployeesCustomRequest extends PageableRequest {

    String code;

    String name;

    String email;

    String city;

    String status;

    String role;

}
