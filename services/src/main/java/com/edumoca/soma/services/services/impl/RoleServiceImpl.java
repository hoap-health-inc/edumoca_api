package com.edumoca.soma.services.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.models.RoleResponse;
import com.edumoca.soma.entities.dtos.RoleDTO;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.Role;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.RoleRepository;
import com.edumoca.soma.services.services.RoleService;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	private final InstitutionRepository institutionRepository;

	private final ModelMapper modelMapper;

	@Override
	public RoleDTO createRole(Role role) {
		return modelMapper.map(roleRepository.save(role),RoleDTO.class);
	}

	@Override
	public RoleDTO updateRole(Role role,Integer roleId) {
		Optional<RoleResponse> role1 =  Optional.ofNullable(roleRepository.getRoleByAndRoleId(roleId));
		if(role1.isPresent())
			role.setRoleId(roleId);
		return modelMapper.map(roleRepository.save(role),RoleDTO.class);

	}
	
	@Override
	public List<RoleDTO> getAllRolesByInstitution(Integer institutionId){
		return roleRepository.getAllRolesByInstitutionId(institutionId).stream().map(r->{
			RoleDTO roleDTO = new RoleDTO();
			BeanUtils.copyProperties(r,roleDTO);
			return roleDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public RoleDTO getRoleByInstitutionAndRole(Integer institutionId,Integer roleId) {
		Optional<RoleResponse> quickRole = Optional.ofNullable(roleRepository.getRoleByInstitutionIdAndRoleId(institutionId, roleId));
		if(quickRole.isPresent()) {
			RoleDTO roleDTO = new RoleDTO();
			BeanUtils.copyProperties(quickRole.get(),roleDTO);
			return roleDTO;
		}else
			throw new DataNotFoundException("Role with id not found");
	}

	@Override
	public Map<String, Set<RoleDTO>> loadRoles(XSSFSheet rolesSheet, String rolesSheetName) {
		Map<String,Set<RoleDTO>> rolesMap = new HashMap<>();
		Set<RoleDTO> rolesSet = new HashSet<>();
		rolesSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Role role = new Role();
				role.setRoleName(row.getCell(0).getStringCellValue());
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				institution.ifPresent(role::setInstitution);
				roleRepository.save(role);
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(role.getRoleId());
				roleDTO.setRoleName(role.getRoleName());
				rolesSet.add(roleDTO);
			}
		});
		rolesMap.put(rolesSheetName,rolesSet);
       return rolesMap;
	}

}
