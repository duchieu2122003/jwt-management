package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.request.MaMissionsCreateRequest;
import com.example.server.core.manager.model.request.MaMissionsUpdateRequest;
import com.example.server.core.manager.model.response.MaMissionsResponse;
import com.example.server.core.manager.model.response.MaMissionsSaveResponse;
import com.example.server.core.manager.repository.MaMissionsRepository;
import com.example.server.core.manager.service.MaMissionsService;
import com.example.server.entity.Missions;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaMissionsServiceImpl implements MaMissionsService {

    private final MaMissionsRepository maMissionsRepository;

    @Override
    public List<MaMissionsResponse> getAll() {
        return maMissionsRepository.getAllMissions();
    }

    @Override
    @Transactional
    public MaMissionsSaveResponse create(MaMissionsCreateRequest request) {
        Optional<Missions> missionsFind = maMissionsRepository.findByName(request.getName());
        if (missionsFind.isEmpty()) {
            Missions missionsAdd = Missions.builder()
                    .name(request.getName())
                    .descriptions(request.getDescriptions())
                    .build();
            Missions save = maMissionsRepository.save(missionsAdd);
            return MaMissionsSaveResponse.builder()
                    .id(save.getId())
                    .name(save.getName())
                    .descriptions(save.getDescriptions())
                    .build();
        } else {
            throw new RestApiException(Message.MISSION_NAME_EXSIST);
        }
    }


    @Override
    @Transactional
    public MaMissionsSaveResponse update(MaMissionsUpdateRequest request) {
        Missions findMissions = maMissionsRepository
                .findById(request.getId()).orElseThrow(() -> new RestApiException(Message.MISSIONS_NOT_EXSIST));
        Optional<Missions> findMissionsName = maMissionsRepository
                .findByName(request.getName());
        if (findMissionsName.isPresent() && !findMissions.getId().equals(findMissionsName.get().getId())) {
            throw new RestApiException(Message.MISSION_NAME_EXSIST);
        }
        Missions save = Missions.builder()
                .id(findMissions.getId())
                .name(request.getName())
                .descriptions(request.getDescriptions())
                .build();
        Missions saved = maMissionsRepository.save(save);
        return MaMissionsSaveResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .descriptions(saved.getDescriptions())
                .build();
    }

    @Override
    @Transactional
    public boolean deleteMissions(String id) {
        Missions missions = maMissionsRepository.findById(id)
                .orElseThrow(() -> new RestApiException(Message.MISSIONS_NOT_EXSIST));
        Integer countMissionUsed = maMissionsRepository.countMissionsUsed(id);
        if (countMissionUsed >= 1) {
            throw new RestApiException(Message.MISSION_USED);
        }
        maMissionsRepository.delete(missions);
        return true;
    }
}
