package com.example.server.core.admin.service.implement;

import com.example.server.core.admin.model.request.AdDepartmentCreateRequest;
import com.example.server.core.admin.model.request.AdDepartmentUpdateRequest;
import com.example.server.core.admin.model.response.AdDepartmentsGetResponse;
import com.example.server.core.admin.model.response.AdDepartmentsResponse;
import com.example.server.core.admin.repository.AdDepartmentsRepository;
import com.example.server.core.admin.service.AdDepartmentsService;
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
public class AdDepartmentServiceImpl implements AdDepartmentsService {

    private final AdDepartmentsRepository adDepartmentsRepository;

    @Override
    public List<AdDepartmentsResponse> getAllDepartmentActive() {
        return adDepartmentsRepository.findAllByStatus(StatusDepartment.ACTIVE);
    }

    @Override
    public List<AdDepartmentsGetResponse> getAll() {
        List<Departments> list = adDepartmentsRepository.findAll();
        List<AdDepartmentsGetResponse> listResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AdDepartmentsGetResponse objResult = AdDepartmentsGetResponse.builder()
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
