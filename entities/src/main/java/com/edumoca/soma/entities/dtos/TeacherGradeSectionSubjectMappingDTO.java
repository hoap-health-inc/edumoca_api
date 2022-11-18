package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMapping;
import lombok.Data;

@Data
public class TeacherGradeSectionSubjectMappingDTO {
    private int teacherGradeSectionSubjectMappingId;
    private GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping;
    private int teacherId;
    private String teacherName;
    private int subjectId;
    private String subjectName;
}
