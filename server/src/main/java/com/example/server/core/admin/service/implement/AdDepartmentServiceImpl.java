package com.example.server.core.admin.service.implement;

import com.example.server.core.admin.model.mapper.AdDepartmentMapper;
import com.example.server.core.admin.model.request.AdDepartmentCreateRequest;
import com.example.server.core.admin.model.request.AdDepartmentUpdateRequest;
import com.example.server.core.admin.model.response.AdDepartmentsCustomResponse;
import com.example.server.core.admin.model.response.AdDepartmentsGetResponse;
import com.example.server.core.admin.repository.AdDepartmentsRepository;
import com.example.server.core.admin.service.AdDepartmentsService;
import com.example.server.entity.Departments;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.constant.StatusDepartment;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.infrastructure.validation.ValidationChain;
import com.example.server.infrastructure.validation.property.FieldMaxSizeStep;
import com.example.server.infrastructure.validation.property.FieldNotNullStep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class AdDepartmentServiceImpl implements AdDepartmentsService {

    private final AdDepartmentsRepository adDepartmentsRepository;

    private final AdDepartmentMapper adDepartmentMapper;

    @Override
    public List<AdDepartmentsCustomResponse> getAllDepartmentActive() {
        return adDepartmentsRepository.findAllByStatus(StatusDepartment.ACTIVE)
                .stream().map(adDepartmentMapper::departmentsToCustomResponse).collect(Collectors.toList());
    }

    @Override
    public List<AdDepartmentsCustomResponse> getAll() {
        List<Departments> list = adDepartmentsRepository.findAll();
        return list.stream().map(adDepartmentMapper::departmentsToCustomResponse).collect(Collectors.toList());
    }

    @Override
    public AdDepartmentsGetResponse detail(String id) {
        Departments departments = adDepartmentsRepository.findById(id)
                .orElseThrow(() -> new RestApiException(Message.DEPARTMENT_NOT_EXSIST));
        return AdDepartmentsGetResponse.builder()
                .id(departments.getId())
                .name(departments.getName())
                .descriptions(departments.getDescriptions())
                .status(departments.getStatus())
                .build();
    }

    @Override
    @Transactional
    public AdDepartmentsGetResponse create(AdDepartmentCreateRequest request) {
        Optional<Departments> findDepartments = adDepartmentsRepository.findDepartmentsByName(request.getName());
        ValidationChain<AdDepartmentCreateRequest> validationChain = new ValidationChain<>();
        validationChain.addStep(new FieldNotNullStep());
        validationChain.addStep(new FieldMaxSizeStep());
        validationChain.validatedAll(request);

        if (findDepartments.isPresent()) {
            throw new RestApiException(Message.DEPARTMENT_NAME_EXSIST);
        }
        Departments departments = Departments.builder()
                .name(request.getName())
                .descriptions(request.getDescriptions())
                .status(StatusDepartment.ACTIVE)
                .build();
        Departments save = adDepartmentsRepository.save(departments);
        return AdDepartmentsGetResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .descriptions(save.getDescriptions())
                .status(StatusDepartment.ACTIVE)
                .build();
    }

    @Override
    @Transactional
    public AdDepartmentsGetResponse update(AdDepartmentUpdateRequest request) {
        Optional<Departments> findDepartments = adDepartmentsRepository
                .findById(request.getId());
        if (findDepartments.isEmpty()) {
            throw new RestApiException(Message.DEPARTMENT_NOT_EXSIST);
        }
        Optional<Departments> findDepartmentsName = adDepartmentsRepository
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
                    return adDepartmentsRepository.save(exist);
                })
                .orElse(findDepartments.get());
        return AdDepartmentsGetResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .descriptions(save.getDescriptions())
                .status(save.getStatus())
                .build();
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        Optional<Departments> departments = adDepartmentsRepository.findById(id);
        if (departments.isPresent()) {
            List<String> listIdEmployeesInDepartment = adDepartmentsRepository.getIdEmployeeOnIdDepartment(id);
            if (!listIdEmployeesInDepartment.isEmpty()) {
                throw new RestApiException(Message.EMPLOYEES_ON_DEPARTMENT);
            }
            adDepartmentsRepository.delete(departments.get());
            return true;
        }
        return false;
    }

}
