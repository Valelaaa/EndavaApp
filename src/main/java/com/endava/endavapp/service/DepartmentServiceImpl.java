package com.endava.endavapp.service;

import com.endava.endavapp.entity.Department;
import com.endava.endavapp.dto.DepartmentDto;
import com.endava.endavapp.execption.CouldNotAddElement;
import com.endava.endavapp.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream()
                .map(DepartmentDto::departmentDtoFromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public DepartmentDto getDepartmentInformation(final String ID) {
        return departmentRepository.findById(UUID.fromString(ID))
                .map(DepartmentDto::departmentDtoFromEntity).orElse(null);
    }

    @Override
    public boolean addDepartment(final DepartmentDto departmentDTO) throws CouldNotAddElement {
        final Department department = new Department(UUID.randomUUID(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getLocation());
        return departmentRepository.save(department) != null;
    }

    @Override
    public DepartmentDto editDepartment(final DepartmentDto department, final UUID departmentId) {
        if (departmentRepository.findById(departmentId).isPresent()) {
            final Department updateDepartment = departmentRepository.getReferenceById(departmentId);
            updateDepartment.setDepartmentName(department.getDepartmentName());
            updateDepartment.setLocation(department.getLocation());
            return DepartmentDto.departmentDtoFromEntity(updateDepartment);
        } else {
            throw new NoSuchElementException("No such Department");
        }
    }

}