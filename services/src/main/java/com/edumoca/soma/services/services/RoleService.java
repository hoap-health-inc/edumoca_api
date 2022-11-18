package com.edumoca.soma.services.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edumoca.soma.entities.dtos.RoleDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Role;

public interface RoleService {
	RoleDTO createRole(Role role);
	RoleDTO updateRole(Role role,Integer roleId);
	List<RoleDTO> getAllRolesByInstitution(Integer institutionId);
	RoleDTO getRoleByInstitutionAndRole(Integer institutionId,Integer roleId);

	Map<String, Set<RoleDTO>> loadRoles(XSSFSheet rolesSheet, String rolesSheetName);
}
