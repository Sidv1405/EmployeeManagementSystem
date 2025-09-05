package com.sidv.ems.service;

import com.sidv.ems.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(UUID employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(UUID employeeId, EmployeeDto employeeDto);

    EmployeeDto deleteEmployee(UUID employeeId);

    EmployeeDto getEmployeeByName(String employeeName);

    EmployeeDto getEmployeeByEmail(String employeeEmail);
}
