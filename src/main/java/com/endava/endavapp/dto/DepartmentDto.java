package com.endava.endavapp.dto;

import com.endava.endavapp.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DepartmentDto {
    private String departmentName;
    private String location;

    public static DepartmentDto departmentDtoFromEntity(final Department department) {
        return DepartmentDto.builder()
                .departmentName(department.getDepartmentName())
                .location(department.getLocation())
                .build();
    }
}