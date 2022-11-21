package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Department;
import com.edumoca.soma.entities.dtos.DepartmentDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DepartmentService {
	DepartmentDto createDepartment(Department department);
	DepartmentDto updateDepartment(Department department, Integer departmentId);
	List<DepartmentDto> getAllDepartmentsByInstitution(Integer institutionId);
	DepartmentDto getDepartmentByInstitutionAndDepartment(Integer institutionId, Integer departmentId);
	Map<String, Set<DepartmentDto>> loadDepartments(XSSFSheet departmentsSheet, String departmentsSheetName);
}
