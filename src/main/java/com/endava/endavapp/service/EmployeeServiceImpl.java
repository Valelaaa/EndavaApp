package com.endava.endavapp.service;

import com.endava.endavapp.dto.EmployeeDto;
import com.endava.endavapp.entity.Department;
import com.endava.endavapp.entity.Employee;
import com.endava.endavapp.exeption.ElementNotFoundException;
import com.endava.endavapp.repository.DepartmentRepository;
import com.endava.endavapp.repository.EmployeeRepository;
import com.endava.endavapp.validation.AlreadyExistsValidation;
import com.endava.endavapp.validation.DoNotExistValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AlreadyExistsValidation alreadyExistsValidation;
    private final DoNotExistValidation doNotExistValidation;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(EmployeeDto::employeeDtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeInfoById(final String ID) {
        return employeeRepository
                .findById(ID)
                .map(EmployeeDto::employeeDtoFromEntity)
                .orElseThrow(() -> new ElementNotFoundException("Employee not found"));
    }


    @Override
    public boolean addEmployee(final EmployeeDto employeeDTO) {
        alreadyExistsValidation.employeeAlreadyExistsValidation(employeeDTO);

        final Department department = departmentRepository
                .findByDepartmentNameAndLocation(
                        employeeDTO
                                .getDepartment()
                                .getDepartmentName(),
                        employeeDTO
                                .getDepartment()
                                .getLocation()
                )
                .orElse(null);

        final Employee employee = Employee
                .builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .salary(employeeDTO.getSalary())
                .id(UUID
                            .randomUUID()
                            .toString())
                .department(department)
                .build();
        return nonNull(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto editEmployee(final EmployeeDto employeeDTO, final String employeeDestinationId) {
        alreadyExistsValidation.employeeAlreadyExistsValidation(employeeDTO);

        final String departmentName = employeeDTO
                .getDepartment()
                .getDepartmentName();

        final Department department = departmentRepository
                .findFirstByDepartmentName(departmentName)
                .orElse(null);

        final Employee updateEmployee = employeeRepository
                .getReferenceById(employeeDestinationId);
        updateEmployee.setFirstName(employeeDTO.getFirstName());
        updateEmployee.setEmail(employeeDTO.getEmail());
        updateEmployee.setLastName(employeeDTO.getLastName());
        updateEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        updateEmployee.setSalary(employeeDTO.getSalary());
        updateEmployee.setDepartment(department);
        return EmployeeDto.employeeDtoFromEntity(updateEmployee);
    }
}
