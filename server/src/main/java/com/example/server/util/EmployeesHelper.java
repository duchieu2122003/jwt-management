package com.example.server.util;

import com.example.server.entity.Employees;
import com.example.server.repositoty.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

/**
 * @author duchieu212
 */
@Component
public class EmployeesHelper {

    private static EmployeesRepository employeesRepository;

    @Autowired
    public EmployeesHelper(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public static String generateEmployeeCode() {
        String startCode = "NV";
        int numberLength = 4;
        String randomNumber = generateRandomNumber(numberLength);
        String employeeCode = startCode + randomNumber;
        while (employeeCodeExistsInDatabase(employeeCode)) {
            randomNumber = generateRandomNumber(numberLength);
            employeeCode = startCode + randomNumber;
        }
        return employeeCode;
    }

    private static String generateRandomNumber(int n) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int randomNumber = random.nextInt(10);
            number.append(randomNumber);
        }
        return number.toString();
    }

    private static boolean employeeCodeExistsInDatabase(String employeeCode) {
        Optional<Employees> employees = employeesRepository.findEmployeesByCode(employeeCode);
        if (employees.isPresent()) {
            return true;
        }
        return false;
    }
}
