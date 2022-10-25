package com.endava.endavapp.controller;

import com.endava.endavapp.dto.EmployeeDto;
import com.endava.endavapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{employee_id}")
    public EmployeeDto getEmployeeInfoById(@PathVariable("employee_id") final String id) {
        return employeeService.getEmployeeInfoById(id);
    }

    @PostMapping
    public boolean addEmployee(@RequestBody final EmployeeDto employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{employee_id}")
    public EmployeeDto editEmployee(
            @RequestBody final EmployeeDto employee, @PathVariable("employee_id") final String id) {
        return employeeService.editEmployee(employee, id);
    }


}