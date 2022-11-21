package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Role;
import com.edumoca.soma.entities.dtos.RoleDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService {
	RoleDto createRole(Role role);
	RoleDto updateRole(Role role, Integer roleId);
	List<RoleDto> getAllRolesByInstitution(Integer institutionId);
	RoleDto getRoleByInstitutionAndRole(Integer institutionId, Integer roleId);

	Map<String, Set<RoleDto>> loadRoles(XSSFSheet rolesSheet, String rolesSheetName);
}
