package com.endava.endavapp.controller;

import com.endava.endavapp.dto.DepartmentDto;
import com.endava.endavapp.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DeparmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAll();
    }

    @GetMapping("/{department_id}")
    public DepartmentDto getDepartmentById(@PathVariable("department_id") final String id) {
        return departmentService.getDepartmentInformation(id);
    }

    @PostMapping
    @SneakyThrows
    public boolean addDepartment(@RequestBody final DepartmentDto department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping("/{department_id}")
    public DepartmentDto editEmployee(@RequestBody final DepartmentDto departmentDto,
                                      @PathVariable("department_id") final String id) {
        return departmentService.editDepartment(departmentDto, UUID.fromString(id));
    }
}
