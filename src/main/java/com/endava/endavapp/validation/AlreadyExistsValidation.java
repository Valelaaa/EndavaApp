package com.endava.endavapp.validation;

import com.endava.endavapp.exeption.AlreadyExistException;
import com.endava.endavapp.repository.DepartmentRepository;
import com.endava.endavapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlreadyExistsValidation {
    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public void validateDepartmentExistence(final String name, final String location) {
        if (departmentRepository.findByDepartmentNameAndLocation(name,location).isPresent()) {
            throw new AlreadyExistException("Department already exists");
        }
    }
    public void validateDepartmentNameExistence(final String name) {
        if (departmentRepository.findFirstByDepartmentName(name).isPresent()) {
            throw new AlreadyExistException("Department already exists");
        }
    }
    public void validateEmployeeByEmailExistence(final String email) {
        if (employeeRepository.findByEmail(email).isPresent()) {
            throw new AlreadyExistException("Employee already exists");
        }
    }

    public void validateEmployeeByPhoneNumberExistence(final String phoneNumber) {
        if (employeeRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new AlreadyExistException("Employee already exists");
        }
    }
}
