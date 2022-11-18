package com.edumoca.soma.services.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.ScheduleStatus;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.ScheduleStatusRepository;
import com.edumoca.soma.services.services.ScheduleStatusService;

@Service
public class ScheduleStatusServiceImpl implements ScheduleStatusService{
	
//	@Autowired
//	private ScheduleStatusRepository scheduleStatusRepository;
//
//	@Override
//	public ScheduleStatus createScheduleStatus(ScheduleStatus scheduleStatus) {
//		return scheduleStatusRepository.save(scheduleStatus);
//	}
//
//	@Override
//	public ScheduleStatus updateScheduleStatus(ScheduleStatus scheduleStatus) {
//		return scheduleStatusRepository.save(scheduleStatus);
//	}
//
//	@Override
//	public ScheduleStatus getScheduleStatusById(Integer scheduleStatusId) {
//		Optional<ScheduleStatus> scheduleStatus = scheduleStatusRepository.findById(scheduleStatusId);
//		if(scheduleStatus.isPresent())
//			return scheduleStatus.get();
//		else
//			throw new DataNotFoundException("ScheduleStatus with id not found.");
//	}
//
//	@Override
//	public void deleteScheduleStatusById(Integer scheduleStatusId) {
//		scheduleStatusRepository.deleteById(scheduleStatusId);
//	}

	
	
}
