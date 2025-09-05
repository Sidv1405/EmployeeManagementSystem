package com.sidv.ems.service.impl;

import com.sidv.ems.dto.EmployeeDto;
import com.sidv.ems.entity.Employee;
import com.sidv.ems.exception.DuplicateEmailException;
import com.sidv.ems.exception.EmployeeNotFoundException;
import com.sidv.ems.mapper.EmployeeMapper;
import com.sidv.ems.repository.EmployeeRepository;
import com.sidv.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.maptoEmployee(employeeDto);

        Employee savedEmployee;
        try {
            savedEmployee = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Duplicate email: " + employeeDto.getEmail());
        }

        return EmployeeMapper.maptoEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

        return EmployeeMapper.maptoEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        return employeeList.stream().map(EmployeeMapper::maptoEmployeeDto).toList();
    }

    @Override
    public EmployeeDto updateEmployee(UUID employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.maptoEmployeeDto(updatedEmployee);
    }

    @Override
    public EmployeeDto deleteEmployee(UUID employeeId) {
        return null;
    }

    @Override
    public EmployeeDto getEmployeeByName(String employeeName) {
        return null;
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String employeeEmail) {
        return null;
    }
}
