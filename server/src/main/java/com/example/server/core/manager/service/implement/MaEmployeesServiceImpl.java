package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.response.MaEmployeesResponse;
import com.example.server.core.manager.repository.MaEmployeesRepository;
import com.example.server.core.manager.service.MaEmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaEmployeesServiceImpl implements MaEmployeesService {

    private final MaEmployeesRepository maEmployeesRepository;

    @Override
    public List<MaEmployeesResponse> getAllEmployeesNotDepartment() {
        return maEmployeesRepository.getAllEmployeesNotDepartment();
    }
}
