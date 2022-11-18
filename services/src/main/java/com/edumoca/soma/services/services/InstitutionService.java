package com.edumoca.soma.services.services;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.edumoca.soma.entities.dtos.InstitutionDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import com.edumoca.soma.entities.Institution;

public interface InstitutionService {
	InstitutionDTO createInstitution(Institution institution);
	InstitutionDTO updateInstitution(Institution institution,Integer institutionId);
	List<InstitutionDTO> getAllInstitutions();
	InstitutionDTO getInstitutionById(Integer institutionId);

	void deleteInstitution(Integer institutionId);
	Map<String, Set<InstitutionDTO>> loadInstitution(XSSFSheet institutionSheet, String instituteSheetName);
}
