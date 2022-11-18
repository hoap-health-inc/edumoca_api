package com.edumoca.soma.services.controllers;

import java.util.List;

import com.edumoca.soma.entities.dtos.DepartmentDTO;
import com.edumoca.soma.entities.models.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.Department;
import com.edumoca.soma.services.services.DepartmentService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/department")
@AllArgsConstructor
public class DepartmentController {
	
    private final DepartmentService departmentService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
		return new ResponseEntity<>(departmentService.createDepartment(modelMapper.map(departmentRequest,Department.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{departmentId}")
	public ResponseEntity<DepartmentDTO> updateDepartment(@Valid @RequestBody DepartmentRequest departmentRequest,@PathVariable("departmentId") Integer departmentId) {
		return new ResponseEntity<>(departmentService.updateDepartment(modelMapper.map(departmentRequest,Department.class),departmentId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartmentDTO>> getAllDepartmentsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(departmentService.getAllDepartmentsByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{deptId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentDTO> getDepartmentByInstitutionAndDepartment(@PathVariable("institutionId") Integer institutionId,@PathVariable("deptId") Integer departmentId) {
		return new ResponseEntity<>(departmentService.getDepartmentByInstitutionAndDepartment(institutionId,departmentId),HttpStatus.OK);
	}
}
