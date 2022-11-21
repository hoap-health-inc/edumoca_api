package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Role;
import com.edumoca.soma.entities.dtos.RoleDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.RoleResponse;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.entities.repositories.RoleRepository;
import com.edumoca.soma.services.services.RoleService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	private final InstitutionRepository institutionRepository;

	private final ModelMapper modelMapper;

	@Override
	public RoleDto createRole(Role role) {
		return modelMapper.map(roleRepository.save(role), RoleDto.class);
	}

	@Override
	public RoleDto updateRole(Role role, Integer roleId) {
		Optional<RoleResponse> role1 =  Optional.ofNullable(roleRepository.getRoleByAndRoleId(roleId));
		if(role1.isPresent())
			role.setRoleId(roleId);
		return modelMapper.map(roleRepository.save(role), RoleDto.class);

	}
	
	@Override
	public List<RoleDto> getAllRolesByInstitution(Integer institutionId){
		return roleRepository.getAllRolesByInstitutionId(institutionId).stream().map(r->{
			RoleDto roleDTO = new RoleDto();
			BeanUtils.copyProperties(r,roleDTO);
			return roleDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public RoleDto getRoleByInstitutionAndRole(Integer institutionId, Integer roleId) {
		Optional<RoleResponse> quickRole = Optional.ofNullable(roleRepository.getRoleByInstitutionIdAndRoleId(institutionId, roleId));
		if(quickRole.isPresent()) {
			RoleDto roleDTO = new RoleDto();
			BeanUtils.copyProperties(quickRole.get(),roleDTO);
			return roleDTO;
		}else
			throw new DataNotFoundException("Role with id not found");
	}

	@Override
	public Map<String, Set<RoleDto>> loadRoles(XSSFSheet rolesSheet, String rolesSheetName) {
		Map<String,Set<RoleDto>> rolesMap = new HashMap<>();
		Set<RoleDto> rolesSet = new HashSet<>();
		rolesSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Role role = new Role();
				role.setRoleName(row.getCell(0).getStringCellValue());
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				institution.ifPresent(role::setInstitution);
				roleRepository.save(role);
				RoleDto roleDTO = new RoleDto();
				roleDTO.setRoleId(role.getRoleId());
				roleDTO.setRoleName(role.getRoleName());
				rolesSet.add(roleDTO);
			}
		});
		rolesMap.put(rolesSheetName,rolesSet);
       return rolesMap;
	}

}
