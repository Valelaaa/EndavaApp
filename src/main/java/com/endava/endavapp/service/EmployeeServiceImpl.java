package com.endava.endavapp.service;

import com.endava.endavapp.entity.Employee;
import com.endava.endavapp.dto.EmployeeDto;
import com.endava.endavapp.execption.CouldNotAddElement;
import com.endava.endavapp.repository.DepartmentRepository;
import com.endava.endavapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeDto::employeeDtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeInfoById(final String ID) {
        return employeeRepository.findById(UUID.fromString(ID))
                .map(EmployeeDto::employeeDtoFromEntity)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public boolean addEmployee(final EmployeeDto employeeDTO) throws CouldNotAddElement {
        final Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .salary(employeeDTO.getSalary())
                .id(UUID.randomUUID())
                .department(departmentRepository.findByDepartmentName(employeeDTO
                        .getDepartmentName())).build();

        return employeeRepository.save(employee) != null;
    }

    @Override
    public EmployeeDto editEmployee(final EmployeeDto employeeDTO, final UUID employeeDestination) {
        if (employeeRepository.findById(employeeDestination).isPresent()) {
            final Employee updateEmployee = employeeRepository.getReferenceById(employeeDestination);
            updateEmployee.setFirstName(employeeDTO.getFirstName());
            updateEmployee.setEmail(employeeDTO.getEmail());
            updateEmployee.setLastName(employeeDTO.getLastName());
            updateEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
            updateEmployee.setSalary(employeeDTO.getSalary());
            updateEmployee.setDepartment(departmentRepository.findByDepartmentName(employeeDTO
                    .getDepartmentName()));
            return EmployeeDto.employeeDtoFromEntity(updateEmployee);
        } else {
            throw new NoSuchElementException("No such Employee");
        }
    }
}
