package com.example.server.core.common.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duchieu212
 */
@Getter
@Setter
@Builder
public class AuthenticationRequest {

    private String email;
    private String password;
}
