package com.endava.endavapp.validation;

import com.endava.endavapp.dto.EmployeeDto;
import com.endava.endavapp.exeption.ElementNotFoundException;
import com.endava.endavapp.repository.DepartmentRepository;
import com.endava.endavapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoNotExistValidation {
    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public void validateDepartmentExistence(final String name, final String location) {
        if (!departmentRepository.findByDepartmentNameAndLocation(name,location).isPresent()) {
            throw new ElementNotFoundException("Department not found");
        }
    }

    public void validateDepartmentNameExistence(final String name) {
        if (!departmentRepository.findFirstByDepartmentName(name).isPresent()) {
            throw new ElementNotFoundException("Department not found");
        }
    }
    public void validateEmployeeByEmailExistence(final String email) {
        if (!employeeRepository.findByEmail(email).isPresent()) {
            throw new ElementNotFoundException("Employee not found");
        }
    }

    public void validateEmployeeByPhoneNumberExistence(final String phoneNumber) {
        if (!employeeRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new ElementNotFoundException("Employee not found");
        }
    }
    public void validateEmployeeExistence(final EmployeeDto employeeDto) {
        validateEmployeeByPhoneNumberExistence(employeeDto.getPhoneNumber());
        validateEmployeeByEmailExistence(employeeDto.getPhoneNumber());
    }
}
