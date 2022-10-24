package com.endava.endavapp.dto;

import com.endava.endavapp.dto.DepartmentDto;
import com.endava.endavapp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private DepartmentDto department;
    private String phoneNumber;
    private double salary;
    public static EmployeeDto employeeDtoFromEntity(final Employee employee) {
        return EmployeeDto.builder().firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .department(DepartmentDto.departmentDtoFromEntity(employee.getDepartmentId()))
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .build();
    }
}