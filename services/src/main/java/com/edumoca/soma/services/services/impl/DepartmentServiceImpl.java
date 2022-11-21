package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Department;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.dtos.DepartmentDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.DepartmentResponse;
import com.edumoca.soma.entities.repositories.DepartmentRepository;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.services.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

	private final DepartmentRepository departmentRepository;
	private final InstitutionRepository institutionRepository;
 	private final ModelMapper modelMapper;
	@Override
	public DepartmentDto createDepartment(Department department) {
		return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
	}

	@Override
	public DepartmentDto updateDepartment(Department department, Integer departmentId) {
		Optional<DepartmentResponse> department1 = Optional.ofNullable(departmentRepository.getDepartmentByDepartmentId(departmentId));
		if(department1.isPresent())
			department.setDeptId(departmentId);
		return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
	}
	
	@Override
	public List<DepartmentDto> getAllDepartmentsByInstitution(Integer institutionId) {
		return departmentRepository.getAllDepartmentsByInstitutionId(institutionId).stream().map(d->{
			return modelMapper.map(d, DepartmentDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto getDepartmentByInstitutionAndDepartment(Integer institutionId, Integer departmentId) {
		Optional<DepartmentResponse> quickDepartment = Optional.ofNullable(departmentRepository.getDepartmentByInstitutionIdAndDepartmentId(institutionId, departmentId));
		if(quickDepartment.isPresent()) {
			return modelMapper.map(quickDepartment.get(), DepartmentDto.class);
		}else
			throw new DataNotFoundException("Department with id not found.");
	}

	@Override
	public Map<String, Set<DepartmentDto>> loadDepartments(XSSFSheet departmentsSheet, String departmentsSheetName) {
		Map<String,Set<DepartmentDto>> departmentsMap = new HashMap<>();
		Set<DepartmentDto> departmentsSet = new HashSet<>();
		departmentsSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0) {
				Department department = new Department();
				department.setDeptName(row.getCell(0).getStringCellValue());
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				institution.ifPresent(department::setInstitution);
				departmentRepository.save(department);
				DepartmentDto departmentDTO = new DepartmentDto();
				departmentDTO.setDepartmentId(department.getDeptId());
				departmentDTO.setDepartmentName(department.getDeptName());
				departmentsSet.add(departmentDTO);
			}
		});
		departmentsMap.put(departmentsSheetName,departmentsSet);
		return departmentsMap;
	}
}
