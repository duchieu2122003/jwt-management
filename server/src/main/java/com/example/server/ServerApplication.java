package com.example.server;

import com.example.server.entity.Departments;
import com.example.server.entity.Employees;
import com.example.server.entity.Missions;
import com.example.server.infrastructure.constant.Gender;
import com.example.server.infrastructure.constant.Role;
import com.example.server.infrastructure.constant.StatusDepartment;
import com.example.server.infrastructure.constant.StatusEmployee;
import com.example.server.repositoty.DepartmentsRepository;
import com.example.server.repositoty.EmployeesRepository;
import com.example.server.repositoty.MissionsRepository;
import com.example.server.util.DateConverter;
import com.example.server.util.EmployeesHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

//    @Autowired
//    public ServerApplication(DepartmentsRepository departmentsRepository, EmployeesRepository employeesRepository, MissionsRepository missionsRepository) {
//        this.departmentsRepository = departmentsRepository;
//        this.employeesRepository = employeesRepository;
//        this.missionsRepository = missionsRepository;
//    }
//
//    private final DepartmentsRepository departmentsRepository;
//    private final EmployeesRepository employeesRepository;
//    private final MissionsRepository missionsRepository;
//
//    @Bean
//    ApplicationRunner applicationRunner() {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        return args -> {
//            List<Missions> listMission = new ArrayList<>();
//            Missions missions1 = Missions.builder()
//                    .id("d")
//                    .name("Dev")
//                    .descriptions("Coder")
//                    .build();
//            Missions missions2 = Missions.builder()
//                    .id("t")
//                    .name("Test")
//                    .descriptions("TESTER")
//                    .build();
//            listMission.add(missions1);
//            listMission.add(missions2);
//            missionsRepository.saveAll(listMission);
//
//            List<Departments> departmentsList = new ArrayList<>();
//            Departments departments1 = Departments.builder()
//                    .name("Kế toán")
//                    .descriptions("Phòng ban quản lý về phần mềm kế toán")
//                    .status(StatusDepartment.ACTIVE)
//                    .build();
//            Departments departments2 = Departments.builder()
//                    .name("Booking")
//                    .descriptions("Phòng ban quản lý về việc đặt book trước ở tất cả dịch vụ")
//                    .status(StatusDepartment.ACTIVE)
//                    .build();
//            Departments departments3 = Departments.builder()
//                    .name("Bán hàng")
//                    .descriptions("Phòng ban quản lý về phần mềm bán hàng toàn quốc")
//                    .status(StatusDepartment.ACTIVE)
//                    .build();
//            departmentsList.add(departments1);
//            departmentsList.add(departments2);
//            departmentsList.add(departments3);
//            List<Departments> departmentsListSave = departmentsRepository.saveAll(departmentsList);
////          ADMIN
//            List<Employees> employeesList = new ArrayList<>();
//            Employees employees = Employees.builder()
//                    .code(EmployeesHelper.generateEmployeeCode())
//                    .lastName("Hiệu")
//                    .firstName("Nguyễn Đức")
//                    .email("admin@gmail.com")
//                    .password(passwordEncoder.encode("a"))
//                    .birthday(DateConverter.convertStringToDate("2003-12-02"))
//                    .address("Hồng Thuận")
//                    .street("Giao Thủy")
//                    .city("Nam Định")
//                    .country("Việt Nam")
//                    .role(Role.ADMIN)
//                    .gender(Gender.MALE)
//                    .status(StatusEmployee.ACTIVE)
//                    .departments(null)
//                    .build();
////          MANAGER
//            Employees employees1 = Employees.builder()
//                    .code(EmployeesHelper.generateEmployeeCode())
//                    .lastName("Manager")
//                    .firstName("Nguyễn Đức")
//                    .email("manager@gmail.com")
//                    .password(passwordEncoder.encode("a"))
//                    .birthday(DateConverter.convertStringToDate("2003-12-01"))
//                    .address("Giao Thanh")
//                    .street("Giao Thủy")
//                    .city("Nam Định")
//                    .country("Việt Nam")
//                    .role(Role.MANAGER)
//                    .gender(Gender.MALE)
//                    .departments(null)
//                    .status(StatusEmployee.ACTIVE)
//                    .build();
////          STAFF
//            Employees employees2 = Employees.builder()
//                    .code(EmployeesHelper.generateEmployeeCode())
//                    .lastName("Staff")
//                    .firstName("Nguyễn Đức")
//                    .email("staff@gmail.com")
//                    .password(passwordEncoder.encode("a"))
//                    .birthday(DateConverter.convertStringToDate("2001-01-01"))
//                    .address("Giao Lạc")
//                    .street("Giao Thủy")
//                    .city("Nam Định")
//                    .country("Việt Nam")
//                    .role(Role.STAFF)
//                    .gender(Gender.FEMALE)
//                    .status(StatusEmployee.ACTIVE)
//                    .departments(null)
//                    .build();
//            employeesList.add(employees);
//            employeesList.add(employees1);
//            employeesList.add(employees2);
//            employeesRepository.saveAll(employeesList);
//            log.error("===================CREATE EMPLOYEE SUSSCESS====================");
//        };
//    }

}