package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Department;
import com.edumoca.soma.entities.models.DepartmentResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer>{

    @Query("select new com.edumoca.soma.entities.models.DepartmentResponse(dept.deptId,dept.deptName) from Department dept where dept.institution.institutionId= :institutionId")
    public List<DepartmentResponse> getAllDepartmentsByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.DepartmentResponse(dept.deptId,dept.deptName) from Department dept where dept.institution.institutionId= :institutionId and dept.deptId= :departmentId")
    public DepartmentResponse getDepartmentByInstitutionIdAndDepartmentId(@Param("institutionId") Integer institutionId, @Param("departmentId") Integer departmentId);

    @Query("select new com.edumoca.soma.entities.models.DepartmentResponse(dept.deptId,dept.deptName) from Department dept where dept.deptId= :departmentId")
    public DepartmentResponse getDepartmentByDepartmentId(@Param("departmentId") Integer departmentId);
}
