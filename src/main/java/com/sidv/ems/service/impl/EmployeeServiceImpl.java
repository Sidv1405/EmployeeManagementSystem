package com.sidv.ems.service.impl;

import com.sidv.ems.dto.EmployeeDto;
import com.sidv.ems.entity.Employee;
import com.sidv.ems.exception.EmployeeNotFoundException;
import com.sidv.ems.mapper.EmployeeMapper;
import com.sidv.ems.repository.EmployeeRepository;
import com.sidv.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.maptoEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.maptoEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

        return EmployeeMapper.maptoEmployeeDto(employee);
    }
}
