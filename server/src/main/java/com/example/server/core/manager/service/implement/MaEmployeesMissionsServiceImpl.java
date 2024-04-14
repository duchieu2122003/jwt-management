package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.request.MaEmployeesMissionsDeleteRequest;
import com.example.server.core.manager.model.request.MaEmployeesMissionsRequest;
import com.example.server.core.manager.model.response.MaEmployeesMissionsCreateResponse;
import com.example.server.core.manager.model.response.MaEmployeesMissionsResponse;
import com.example.server.core.manager.repository.MaDepartmentRepository;
import com.example.server.core.manager.repository.MaEmployeesRepository;
import com.example.server.core.manager.repository.MaMissionsRepository;
import com.example.server.core.manager.service.MaEmployeesMissionsService;
import com.example.server.entity.Departments;
import com.example.server.entity.Employees;
import com.example.server.entity.Missions;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author duchieu212
 */
@Service
@RequiredArgsConstructor
public class MaEmployeesMissionsServiceImpl implements MaEmployeesMissionsService {


    private final MaDepartmentRepository maDepartmentRepository;

    private final MaEmployeesRepository maEmployeesRepository;

    private final MaMissionsRepository maMissionsRepository;

    @Override
    public List<MaEmployeesMissionsResponse> getAllEmployeesMissions(String idDepartment) {
        return maEmployeesRepository.getMaListEmployeeCustom(idDepartment);
    }

    @Override
    @Transactional
    public List<MaEmployeesMissionsCreateResponse> create(List<MaEmployeesMissionsRequest> requests) {
        List<Departments> departmentsList = maDepartmentRepository.findAll();
        List<Missions> missionsList = maMissionsRepository.findAll();
        List<Employees> listEmployees = maEmployeesRepository.getListEmployeesNotDepartment();
        List<Employees> listEmployeesAdd = new ArrayList<>();
        for (MaEmployeesMissionsRequest request : requests) {
            Employees employee = listEmployees.stream()
                    .filter(e -> e.getId().equals(request.getEmployeesId()))
                    .findFirst()
                    .orElse(null);

            if (employee != null) {
                Set<Missions> missionsAdd = new HashSet<>();
                request.getMissionsListId().forEach(missionId -> {
                    Missions mission = missionsList.stream()
                            .filter(m -> m.getId().equals(missionId))
                            .findFirst()
                            .orElse(null);
                    if (mission != null) {
                        missionsAdd.add(mission);
                        mission.getEmployees().add(employee);
                    }
                });

                if (!request.getDepartmentId().equals("")) {
                    Departments department = departmentsList.stream()
                            .filter(d -> d.getId().equals(request.getDepartmentId()))
                            .findFirst()
                            .orElse(null);
                    if (department != null) {
                        employee.setDepartments(department);
                    }
                }
                listEmployeesAdd.add(employee);
            }
        }
        maMissionsRepository.saveAll(missionsList);
        List<Employees> savedEmployees = maEmployeesRepository.saveAll(listEmployeesAdd);

        List<MaEmployeesMissionsCreateResponse> result = new ArrayList<>();
        savedEmployees.forEach(i -> {
            Set<String> missionsJoin = i.getMissions().stream()
                    .map(Missions::getName)
                    .collect(Collectors.toSet());
            String missionsString = String.join(", ", missionsJoin);
            MaEmployeesMissionsCreateResponse obj = MaEmployeesMissionsCreateResponse.builder()
                    .id(i.getId())
                    .code(i.getCode())
                    .email(i.getEmail())
                    .birthday(i.getBirthday())
                    .fullName(i.getFirstName() + " " + i.getLastName())
                    .fullAddress(i.getAddress() + " - " + i.getCity() + " - " + i.getCountry())
                    .gender(i.getGender())
                    .status(i.getStatus())
                    .fullMissions(missionsString)
                    .build();
            result.add(obj);
        });
        return result;
    }

    @Override
    public MaEmployeesMissionsResponse update(List<MaEmployeesMissionsRequest> requests) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(MaEmployeesMissionsDeleteRequest request) {
        Employees employees = maEmployeesRepository.findById(request.getEmployeesId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        employees.setMissions(null);
        employees.setDepartments(null);
        maEmployeesRepository.save(employees);
        return true;
    }
}
