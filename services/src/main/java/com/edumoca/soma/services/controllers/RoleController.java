package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Role;
import com.edumoca.soma.entities.dtos.RoleDto;
import com.edumoca.soma.entities.models.RoleRequest;
import com.edumoca.soma.services.services.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
@AllArgsConstructor
public class RoleController {

	private final RoleService roleService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleRequest roleRequest) {
		return new ResponseEntity<>(roleService.createRole(modelMapper.map(roleRequest,Role.class)), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleRequest roleRequest, @PathVariable("roleId") Integer roleId) {
		return new ResponseEntity<>(roleService.updateRole(modelMapper.map(roleRequest,Role.class),roleId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleDto>> getAllRoles(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(roleService.getAllRolesByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{roleId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleDto> getRole(@PathVariable("institutionId") Integer institutionId, @PathVariable("roleId") Integer roleId) {
		return new ResponseEntity<>(roleService.getRoleByInstitutionAndRole(institutionId,roleId),HttpStatus.OK);
	}

}
