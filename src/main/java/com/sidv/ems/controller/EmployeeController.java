package com.sidv.ems.controller;

import com.sidv.ems.dto.EmployeeDto;
import com.sidv.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //Build Add Employee Rest API
    @PostMapping
    private ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    private ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") UUID employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("{id}")
    private ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") UUID employeeId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
