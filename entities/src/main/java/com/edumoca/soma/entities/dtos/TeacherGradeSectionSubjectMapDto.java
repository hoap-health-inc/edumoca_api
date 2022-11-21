package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import lombok.Data;

@Data
public class TeacherGradeSectionSubjectMapDto {
    private int teacherGradeSectionSubjectMappingId;
    private GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap;
    private int teacherId;
    private String teacherName;
    private int subjectId;
    private String subjectName;
}
