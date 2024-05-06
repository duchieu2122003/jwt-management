package com.example.server.core.manager.model.mapper;

import com.example.server.core.manager.model.response.MaMissionResponse;
import com.example.server.entity.Missions;
import org.springframework.stereotype.Component;

/**
 * @author duchieu212
 */
@Component
public class MaMissionMapper {

    public MaMissionResponse missionToMaMissionResponse(Missions missions) {
        return MaMissionResponse.builder()
                .id(missions.getId())
                .name(missions.getName())
                .descriptions(missions.getDescriptions())
                .salary(missions.getSalary())
                .build();
    }
}
