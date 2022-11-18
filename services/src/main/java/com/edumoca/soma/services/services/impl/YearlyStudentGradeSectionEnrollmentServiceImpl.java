package com.edumoca.soma.services.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.YearlyStudentGradeSectionEnrollment;
import com.edumoca.soma.entities.repositories.YearlyStudentGradeSectionEnrollmentRepository;
import com.edumoca.soma.services.services.YearlyStudentGradeSectionEnrollmentService;

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
