package com.edumoca.soma.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.ScheduleStatus;
import com.edumoca.soma.services.services.ScheduleStatusService;

@RestController
@RequestMapping(value = "/scheduleStatus")
public class ScheduleStatusController {

//	  @Autowired
//	  private ScheduleStatusService scheduleStatusService;
	
//	  @PostMapping
//	  public ScheduleStatus createScheduleStatus(@RequestBody ScheduleStatus status) {
//		  return scheduleStatusService.createScheduleStatus(status);
//	  }
	  
//	  @PutMapping
//	  public ScheduleStatus updateScheduleStatus(@RequestBody ScheduleStatus status) {
//		  return scheduleStatusService.updateScheduleStatus(status);
//	  }
	  
//	  @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//	  public ScheduleStatus getScheduleStatusById(@PathVariable("id") Integer statusId) {
//		  return scheduleStatusService.getScheduleStatusById(statusId);
//	  }
//
//	  @DeleteMapping(value = "/{id}")
//	  public void deleteScheduleStatusById(@PathVariable("id") Integer statusId) {
//		  scheduleStatusService.deleteScheduleStatusById(statusId);
//	  }
}
