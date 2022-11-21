package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.YearlyStudentGradeSectionEnrollment;
import com.edumoca.soma.entities.repositories.YearlyStudentGradeSectionEnrollmentRepository;
import com.edumoca.soma.services.services.YearlyStudentGradeSectionEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearlyStudentGradeSectionEnrollmentServiceImpl implements YearlyStudentGradeSectionEnrollmentService{

	@Autowired
	private YearlyStudentGradeSectionEnrollmentRepository yearlyStudentGradeSectionEnrollmentRepository;

	@Override
	public YearlyStudentGradeSectionEnrollment save(
			YearlyStudentGradeSectionEnrollment yearlyStudentGradeSectionEnrollment) {
		return yearlyStudentGradeSectionEnrollmentRepository.save(yearlyStudentGradeSectionEnrollment);
	}
	
}
