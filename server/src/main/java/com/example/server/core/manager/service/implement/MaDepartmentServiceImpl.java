package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.mapper.MaDepartmentMapper;
import com.example.server.core.manager.model.response.MaDepartmentEmployeesCurrentResponse;
import com.example.server.core.manager.model.response.MaDepartmentResponse;
import com.example.server.core.manager.repository.MaDepartmentRepository;
import com.example.server.core.manager.service.MaDepartmentService;
import com.example.server.entity.Departments;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaDepartmentServiceImpl implements MaDepartmentService {

    private final MaDepartmentRepository maDepartmentRepository;

    private final MaDepartmentMapper maDepartmentMapper;

    @Override
    public List<MaDepartmentResponse> getAll() {
        List<Departments> list = maDepartmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<MaDepartmentResponse> listResult = new ArrayList<>();
        for (Departments departments : list) {
            MaDepartmentResponse objResult = MaDepartmentResponse.builder()
                    .id(departments.getId())
                    .name(departments.getName())
                    .descriptions(departments.getDescriptions())
                    .status(departments.getStatus())
                    .build();
            listResult.add(objResult);
        }
        return listResult;
    }

    @Override
    public MaDepartmentEmployeesCurrentResponse getDepartmentByUserCurrent() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Departments> departmentsOptional = maDepartmentRepository
                .getDepartmentByUserCurrent(email);
        if (departmentsOptional.isEmpty()) {
            throw new RestApiException(Message.YOU_HAVENT_DEPARTMENT);
        }
        return maDepartmentMapper.departmentToMaDepartmentEmployeesCurrentResponse(departmentsOptional.get());
    }
}
