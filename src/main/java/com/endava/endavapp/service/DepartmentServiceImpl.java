package com.endava.endavapp.service;

import com.endava.endavapp.dto.DepartmentDto;
import com.endava.endavapp.entity.Department;
import com.endava.endavapp.exeption.ElementNotFoundException;
import com.endava.endavapp.repository.DepartmentRepository;
import com.endava.endavapp.validation.AlreadyExistsValidation;
import com.endava.endavapp.validation.DoNotExistValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final AlreadyExistsValidation alreadyExistsValidation;
    private final DoNotExistValidation doNotExistValidation;

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository
                .findAll()
                .stream()
                .map(DepartmentDto::departmentDtoFromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public DepartmentDto getDepartmentInformation(final String ID) {
        return departmentRepository
                .findById(ID)
                .map(DepartmentDto::departmentDtoFromEntity)
                .orElseThrow(() ->
                                     new ElementNotFoundException("Department not found"));
    }

    @Override
    public DepartmentDto getDepartmentByName(String name) {
        doNotExistValidation.validateDepartmentNameExistence(name);
        return departmentRepository
                .findFirstByDepartmentName(name)
                .map(DepartmentDto::departmentDtoFromEntity)
                .orElse(null);
    }

    @Override
    public boolean addDepartment(final DepartmentDto departmentDTO) {
        alreadyExistsValidation.validateDepartmentExistence(
                departmentDTO.getDepartmentName(),
                departmentDTO.getLocation()
        );
        final Department department = new Department(
                UUID
                        .randomUUID()
                        .toString(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getLocation()
        );
        return nonNull(departmentRepository.save(department));
    }


    @Override
    public DepartmentDto editDepartment(final DepartmentDto departmentDto, final String departmentId) {
        alreadyExistsValidation.validateDepartmentExistence(
                departmentDto.getDepartmentName(),
                departmentDto.getLocation()
        );
        doNotExistValidation
                .validateDepartmentById(departmentId);

        final Department updateDepartment =
                departmentRepository
                        .getReferenceById(departmentId);

        updateDepartment.setDepartmentName(departmentDto.getDepartmentName());
        updateDepartment.setLocation(departmentDto.getLocation());
        departmentRepository.save(updateDepartment);

        return DepartmentDto.departmentDtoFromEntity(updateDepartment);
    }


    @Override
    public DepartmentDto editDepartmentByName(
            final DepartmentDto departmentDto,
            final String name) {
        doNotExistValidation.validateDepartmentNameExistence(name);
        alreadyExistsValidation.validateDepartmentExistence(
                departmentDto.getDepartmentName(),
                departmentDto.getLocation()
        );

        final String departmentId = departmentRepository
                .findFirstByDepartmentName(name)
                .orElse(null)
                .getDepartmentId();

        final Department updateDepartment =
                departmentRepository.getReferenceById(departmentId);

        updateDepartment.setDepartmentName(departmentDto.getDepartmentName());
        updateDepartment.setLocation(departmentDto.getLocation());
        departmentRepository.save(updateDepartment);

        return DepartmentDto.departmentDtoFromEntity(updateDepartment);
    }

}