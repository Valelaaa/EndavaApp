package com.endava.endavapp.service;


import com.endava.endavapp.dto.EmployeeDto;
import com.endava.endavapp.execption.CouldNotAddElement;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    /**
     * Get a list of all employees from the database
     *
     * @return list of all employees, or empty list if there are no employees
     */
    List<EmployeeDto> getAll();

    /**
     * Get a specific employee information by id
     *
     * @param ID
     * @return employee information
     */
    EmployeeDto getEmployeeInfoById(final String ID);

    /**
     * Add a new employee to the database
     *
     * @param employee - employee which we will add to database
     * @return true if employee added or false in other case
     */
    boolean addEmployee(final EmployeeDto employee) throws CouldNotAddElement;

    /**
     * Edit an employee information in the database
     *
     */
    EmployeeDto editEmployee(final EmployeeDto employeeDTO, final UUID employeeDestination);

}