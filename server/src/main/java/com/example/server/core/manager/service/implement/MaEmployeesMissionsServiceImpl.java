package com.example.server.core.manager.service.implement;

import com.example.server.core.manager.model.request.MaEmployeesMissionsDeleteRequest;
import com.example.server.core.manager.model.request.MaEmployeesMissionsRequest;
import com.example.server.core.manager.model.request.MaEmployeesMissionsUpdateRequest;
import com.example.server.core.manager.model.response.MaEmployeeMissionGetResponse;
import com.example.server.core.manager.model.response.MaEmployeesMissionDetailResponse;
import com.example.server.core.manager.model.response.MaEmployeesMissionUpdateResponse;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null) {
            Employees employeesManager = maEmployeesRepository.findByEmail(email)
                    .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
            if (employeesManager.getDepartments() == null) {
                throw new RestApiException("Không thể thêm nhân viên vào phòng ban bởi vì bạn đang không quản lý phòng ban nào");
            }
        }
        for (MaEmployeesMissionsRequest request : requests) {
            Employees employee = listEmployees.stream()
                    .filter(e -> e.getId().equals(request.getEmployeesId()))
                    .findFirst()
                    .orElse(null);
            if (employee != null) {
                Set<Missions> missionsAdd = new HashSet<>();
                request.getMissionsListId().forEach(missionId -> {
                    missionsList.stream()
                            .filter(m -> m.getId().equals(missionId))
                            .findFirst().ifPresent(missionsAdd::add);
                    employee.setMissions(missionsAdd);
                });

                if (!request.getDepartmentId().equals("")) {
                    departmentsList.stream()
                            .filter(d -> d.getId().equals(request.getDepartmentId()))
                            .findFirst().ifPresent(employee::setDepartments);
                }
                listEmployeesAdd.add(employee);
            }
        }
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
    @Transactional
    public MaEmployeesMissionUpdateResponse update(MaEmployeesMissionsUpdateRequest requests) {
        Employees employees = maEmployeesRepository.findById(requests.getEmployeesId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
        List<Missions> listMissionDb = maMissionsRepository.findAll();
        Set<Missions> missionUpdate = new HashSet<>();
        for (String i : requests.getMissionsListId()) {
            listMissionDb.stream()
                    .filter(db -> db.getId().equals(i))
                    .findFirst().ifPresent(missionUpdate::add);
        }
        employees.setMissions(missionUpdate);
        maEmployeesRepository.save(employees);
        return maEmployeesRepository.getMaEmployeeCustom(employees.getId())
                .orElseThrow(() -> new RestApiException(Message.EMPLOYEE_NOT_EXIST));
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

    @Override
    public MaEmployeesMissionDetailResponse getDetailToUpdateByEmployeeId(String id) {
        Optional<Employees> employees = maEmployeesRepository.getEmployeesById(id);
        List<MaEmployeeMissionGetResponse> missionList = maEmployeesRepository.getMissionEmployeeByIdEmployee(id);
        if (employees.isEmpty()) {
            throw new RestApiException(Message.EMPLOYEE_NOT_EXIST);
        }
        MaEmployeesMissionDetailResponse result = new MaEmployeesMissionDetailResponse();
        result.setEmployeesId(employees.get().getId());
        result.setCode(employees.get().getCode());
        result.setEmail(employees.get().getEmail());
        result.setFullName(employees.get().getFirstName() + " " + employees.get().getLastName());
        List<String> missionsListId = new ArrayList<>();
        missionList.forEach(i -> missionsListId.add(i.getMissionId()));
        result.setMissionsListId(missionsListId);
        return result;
    }
}
