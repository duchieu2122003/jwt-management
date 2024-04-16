package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.request.MaDepartmentCreateRequest;
import com.example.server.core.manager.model.request.MaDepartmentUpdateRequest;
import com.example.server.core.manager.model.response.MaDepartmentResponse;
import com.example.server.core.manager.repository.MaDepartmentRepository;
import com.example.server.core.manager.repository.MaEmployeesRepository;
import com.example.server.core.manager.service.MaDepartmentService;
import com.example.server.entity.Departments;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.constant.StatusDepartment;
import com.example.server.infrastructure.exception.RestApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    private final MaEmployeesRepository maEmployeesRepository;

    @Override
    public List<MaDepartmentResponse> getAll() {
        List<Departments> list = maDepartmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<MaDepartmentResponse> listResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MaDepartmentResponse objResult = MaDepartmentResponse.builder()
                    .id(list.get(i).getId())
                    .stt(i + 1)
                    .name(list.get(i).getName())
                    .descriptions(list.get(i).getDescriptions())
                    .status(list.get(i).getStatus())
                    .build();
            listResult.add(objResult);
        }
        return listResult;
    }

    @Override
    public MaDepartmentResponse detail(String id) {
        Departments departments = maDepartmentRepository.findById(id)
                .orElseThrow(() -> new RestApiException(Message.DEPARTMENT_NOT_EXSIST));
        MaDepartmentResponse objResult = MaDepartmentResponse.builder()
                .id(departments.getId())
                .name(departments.getName())
                .descriptions(departments.getDescriptions())
                .status(departments.getStatus())
                .build();
        return objResult;
    }

    @Override
    @Transactional
    public MaDepartmentResponse create(MaDepartmentCreateRequest request) {
        Optional<Departments> findDepartments = maDepartmentRepository.findDepartmentsByName(request.getName());
        if (findDepartments.isPresent()) {
            throw new RestApiException(Message.DEPARTMENT_NAME_EXSIST);
        }
        Departments departments = Departments.builder()
                .name(request.getName())
                .descriptions(request.getDescriptions())
                .status(StatusDepartment.ACTIVE)
                .build();
        Departments save = maDepartmentRepository.save(departments);
        MaDepartmentResponse objResult = MaDepartmentResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .descriptions(save.getDescriptions())
                .status(StatusDepartment.ACTIVE)
                .build();
        return objResult;
    }

    @Override
    @Transactional
    public MaDepartmentResponse update(MaDepartmentUpdateRequest request) {
        Optional<Departments> findDepartments = maDepartmentRepository
                .findById(request.getId());
        if (findDepartments.isEmpty()) {
            throw new RestApiException(Message.DEPARTMENT_NOT_EXSIST);
        }
        Optional<Departments> findDepartmentsName = maDepartmentRepository
                .findDepartmentsByName(request.getName());
        if (findDepartmentsName.isPresent() && !findDepartments.get().getId().equals(findDepartmentsName.get().getId())) {
            throw new RestApiException(Message.DEPARTMENT_NAME_EXSIST);
        }
        Departments save = findDepartments
                .map(exist -> {
                    exist.setId(request.getId());
                    exist.setName(request.getName());
                    exist.setDescriptions(request.getDescriptions());
                    exist.setStatus(request.getStatus());
                    return maDepartmentRepository.save(exist);
                })
                .orElse(findDepartments.get());
        MaDepartmentResponse objResult = MaDepartmentResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .descriptions(save.getDescriptions())
                .status(save.getStatus())
                .build();
        return objResult;
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        Optional<Departments> departments = maDepartmentRepository.findById(id);
        if (departments.isPresent()) {
            List<String> listIdEmployeesInDepartment = maEmployeesRepository.getIdEmployeeOnIdDepartment(id);
            if (!listIdEmployeesInDepartment.isEmpty()) {
                throw new RestApiException(Message.EMPLOYEES_ON_DEPARTMENT);
            }
            maDepartmentRepository.delete(departments.get());
            return true;
        }
        return false;
    }
}
