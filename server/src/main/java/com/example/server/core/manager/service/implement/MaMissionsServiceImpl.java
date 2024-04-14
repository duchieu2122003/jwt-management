package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.response.MaMissionsResponse;
import com.example.server.core.manager.repository.MaMissionsRepository;
import com.example.server.core.manager.service.MaMissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duchieu212
 */
@Service
public class MaMissionsServiceImpl implements MaMissionsService {

    private MaMissionsRepository maMissionsRepository;

    @Autowired
    public MaMissionsServiceImpl(MaMissionsRepository maMissionsRepository) {
        this.maMissionsRepository = maMissionsRepository;
    }

    @Override
    public List<MaMissionsResponse> getAll() {
        return maMissionsRepository.getAllMissions();
    }
}
