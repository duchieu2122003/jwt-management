package com.example.server.core.common.model.response;

import com.example.server.infrastructure.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author duchieu212
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationHeaderResponse {

    private String id;

    private Role role;

    private String lastName;

}
