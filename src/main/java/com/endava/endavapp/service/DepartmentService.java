package com.endava.endavapp.service;

import com.endava.endavapp.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    /**
     * Get a list of all departments from the database
     *
     * @return all departments from the database
     */
    List<DepartmentDto> getAll();

    /**
     * Get a specific department information by id
     *
     * @param ID
     * @return department Information
     */
    DepartmentDto getDepartmentInformation(final String ID);

    /**
     * Add a new department to the database
     *
     * @param department - employee which we will add to database
     * @return true if employee added or false in other case
     */
    boolean addDepartment(final DepartmentDto department);

    /**
     * Edit a department information in the database
     *
     * @param department what need to change
     * @return saved departmentDto
     */
    DepartmentDto editDepartment(final DepartmentDto department, final String departmentId);


    DepartmentDto getDepartmentByName(String name);

    DepartmentDto editDepartmentByName(DepartmentDto departmentDto, String name);
}