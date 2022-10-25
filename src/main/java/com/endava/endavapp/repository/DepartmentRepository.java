package com.endava.endavapp.repository;

import com.endava.endavapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findFirstByDepartmentName(final String name);

    Optional<Department> findByDepartmentNameAndLocation(final String departmentName, final String location);


}