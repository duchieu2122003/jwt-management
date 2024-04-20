package com.example.server.core.manager.model.response;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author duchieu212
 */
public interface MaMissionsResponse {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.descriptions}")
    String getDescriptions();
}
