package com.endava.endavapp.repository;

import com.endava.endavapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmail(final String email);
    Optional<Employee> findByPhoneNumber(final String email);
}