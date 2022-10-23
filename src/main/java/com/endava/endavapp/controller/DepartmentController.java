package com.endava.endavapp.controller;

import com.endava.endavapp.dto.DepartmentDto;
import com.endava.endavapp.service.DepartmentService;
import lombok.NoArgsConstructor;
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
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
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
    public DepartmentDto editDepartment(@RequestBody final DepartmentDto departmentDto,
                                        @PathVariable("department_id") final String id) {
        return departmentService.editDepartment(departmentDto, UUID.fromString(id));
    }

}
