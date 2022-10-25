package com.endava.endavapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "last_name")
    private String lastName;


    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id")
    private Department department;

    @Email
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "salary")
    @DecimalMin(value = "1.0")
    private double salary;
}