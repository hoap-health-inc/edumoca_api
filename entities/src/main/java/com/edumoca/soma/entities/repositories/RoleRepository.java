package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Role;
import com.edumoca.soma.entities.models.RoleResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>{

    @Query("select new com.edumoca.soma.entities.models.RoleResponse(r.roleId,r.roleName) from Role r where r.institution.institutionId= :institutionId")
    public List<RoleResponse> getAllRolesByInstitutionId(Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.RoleResponse(r.roleId,r.roleName) from Role r where r.institution.institutionId= :institutionId and r.roleId= :roleId")
    public RoleResponse getRoleByInstitutionIdAndRoleId(Integer institutionId, Integer roleId);

    @Query("select new com.edumoca.soma.entities.models.RoleResponse(r.roleId,r.roleName) from Role r where r.roleId= :roleId")
    public RoleResponse getRoleByAndRoleId(@Param("roleId") Integer roleId);
}
