package com.endava.endavapp.repository;

import com.endava.endavapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmail(final String email);

    Optional<Employee> findByPhoneNumber(final String phoneNumber);
}