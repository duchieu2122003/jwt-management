package com.example.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


//    @Autowired
//    private ServerApplication(DepartmentsRepository departmentsRepository, EmployeesRepository employeesRepository) {
//        this.departmentsRepository = departmentsRepository;
//        this.employeesRepository = employeesRepository;
//    }

//
//    @Autowired
//    private DepartmentsRepository departmentsRepository;
//    @Autowired
//    private EmployeesRepository employeesRepository;
//
//
//
//
//    @Bean
//    ApplicationRunner applicationRunner() {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        return args -> {
//            List<Departments> departmentsList = new ArrayList<>();
//            Departments departments1 = Departments.builder()
//                    .name("Kế toán")
//                    .desctiptions("Phòng ban quản lý về phần mềm kế toán")
//                    .status(StatusDepartment.ACTIVE)
//                    .build();
//            Departments departments2 = Departments.builder()
//                    .name("Booking")
//                    .desctiptions("Phòng ban quản lý về việc đặt book trước ở tất cả dịch vụ")
//                    .status(StatusDepartment.ACTIVE)
//                    .build();
//            Departments departments3 = Departments.builder()
//                    .name("Bán hàng")
//                    .desctiptions("Phòng ban quản lý về phần mềm bán hàng toàn quốc")
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
