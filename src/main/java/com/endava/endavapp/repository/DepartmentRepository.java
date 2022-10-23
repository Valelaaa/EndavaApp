package com.endava.endavapp.repository;

import com.endava.endavapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Optional<Department> findFirstByDepartmentName(final String name);
    Optional<Department> findByDepartmentNameAndAndLocation(final String name,final String location);

}