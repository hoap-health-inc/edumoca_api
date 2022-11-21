package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Department;
import com.edumoca.soma.entities.dtos.DepartmentDto;
import com.edumoca.soma.entities.models.DepartmentRequest;
import com.edumoca.soma.services.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/department")
@AllArgsConstructor
public class DepartmentController {
	
    private final DepartmentService departmentService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
		return new ResponseEntity<>(departmentService.createDepartment(modelMapper.map(departmentRequest,Department.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{departmentId}")
	public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentRequest departmentRequest, @PathVariable("departmentId") Integer departmentId) {
		return new ResponseEntity<>(departmentService.updateDepartment(modelMapper.map(departmentRequest,Department.class),departmentId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartmentDto>> getAllDepartmentsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(departmentService.getAllDepartmentsByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{deptId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentDto> getDepartmentByInstitutionAndDepartment(@PathVariable("institutionId") Integer institutionId, @PathVariable("deptId") Integer departmentId) {
		return new ResponseEntity<>(departmentService.getDepartmentByInstitutionAndDepartment(institutionId,departmentId),HttpStatus.OK);
	}
}
