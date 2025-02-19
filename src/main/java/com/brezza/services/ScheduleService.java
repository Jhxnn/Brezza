package com.brezza.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.ScheduleDto;
import com.brezza.models.Schedule;
import com.brezza.repositories.ScheduleRepository;
import com.brezza.repositories.UserRepository;

@Service
public class ScheduleService {

	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	UserRepository userRepository;

	public Schedule findById(UUID id) {
	    return scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}

	public List<Schedule> findAll() {
	    return scheduleRepository.findAll();
	}

	public Schedule createSchedule(ScheduleDto scheduleDto) {
	    var schedule = new Schedule();
	    var inspector = userRepository.findById(scheduleDto.userId()).orElseThrow(() -> new RuntimeException("Cannot be found"));
	    BeanUtils.copyProperties(scheduleDto, schedule);
	    schedule.setInspector(inspector);
	    return scheduleRepository.save(schedule);
	}

	public Schedule updateSchedule(UUID id, ScheduleDto scheduleDto) {
	    var schedule = findById(id);
	    var inspector = userRepository.findById(scheduleDto.userId()).orElseThrow(() -> new RuntimeException("Cannot be found"));
	    BeanUtils.copyProperties(scheduleDto, schedule);
	    schedule.setInspector(inspector);
	    return scheduleRepository.save(schedule);
	}

	public void deleteSchedule(UUID id) {
	    var schedule = findById(id);
	    scheduleRepository.delete(schedule);
	}

}
