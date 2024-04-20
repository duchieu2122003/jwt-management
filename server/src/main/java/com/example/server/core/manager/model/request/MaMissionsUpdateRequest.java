package com.example.server.core.manager.model.request;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.StatusDepartment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaMissionsUpdateRequest {

    String id;

    @NotBlank(message = "Tên nhiệm vụ không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Tên không được quá 200 ký tự")
    String name;

    @Size(max = EntityProperties.LENGTH_DESCRIPTIONS, message = "Mô tả không được quá 1000 ký tự")
    String descriptions;

}
