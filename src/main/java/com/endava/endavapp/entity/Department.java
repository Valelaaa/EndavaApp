package com.endava.endavapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id")
    private UUID departmentId;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "department_name")
    private String departmentName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "location_name")
    private String location;
}