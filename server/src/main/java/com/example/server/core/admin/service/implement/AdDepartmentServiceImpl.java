package com.example.server.core.admin.service.implement;

import com.example.server.core.admin.repository.AdDepartmentsRepository;
import com.example.server.core.admin.response.AdDepartmentsResponse;
import com.example.server.core.admin.service.AdDepartmentsService;
import com.example.server.infrastructure.constant.StatusDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class AdDepartmentServiceImpl implements AdDepartmentsService {

    private final AdDepartmentsRepository adDepartmentsRepository;

    @Override
    public List<AdDepartmentsResponse> getAllDepartmentActive() {
        return adDepartmentsRepository.findAllByStatus(StatusDepartment.ACTIVE);
    }
}
