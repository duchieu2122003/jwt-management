package com.example.server.core.common.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author duchieu212
 */
@Getter
@Setter
public class CoUpdateEmployeeRequest {

    private String id;

    private String fullName;

    private String email;

    private Date birthday;

    private String address;

    private String city;

    private String country;

    private String gender;
}
