package com.example.server.core.common.model.response;


import lombok.Builder;

import java.util.Date;

/**
 * @author duchieu212
 */
@Builder
public class CoDetailEmployeeResponse {

    private String id;

    private String fullName;

    private String email;

    private Date birthday;

    private String address;

    private String city;

    private String country;

    private String code;
}
