package com.edumoca.soma.services.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edumoca.soma.entities.dtos.DepartmentDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Department;

public interface DepartmentService {
	DepartmentDTO createDepartment(Department department);
	DepartmentDTO updateDepartment(Department department,Integer departmentId);
	List<DepartmentDTO> getAllDepartmentsByInstitution(Integer institutionId);
	DepartmentDTO getDepartmentByInstitutionAndDepartment(Integer institutionId,Integer departmentId);
	Map<String, Set<DepartmentDTO>> loadDepartments(XSSFSheet departmentsSheet, String departmentsSheetName);
}
