package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.response.MaDepartmentResponse;
import com.example.server.core.manager.model.response.MaDepartmentUserCurrentResponse;
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

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaDepartmentServiceImpl implements MaDepartmentService {

    private final MaDepartmentRepository maDepartmentRepository;

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
    public MaDepartmentUserCurrentResponse getDepartmentByUserCurrent() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return maDepartmentRepository
                .getDepartmentByUserCurrent(email).orElseThrow(() -> new RestApiException(Message.YOU_HAVENT_DEPARTMENT));
    }
}
