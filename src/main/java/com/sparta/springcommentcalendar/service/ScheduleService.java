package com.sparta.springcommentcalendar.service;

import com.sparta.springcommentcalendar.dto.ScheduleDTO;
import com.sparta.springcommentcalendar.entity.Schedule;
import com.sparta.springcommentcalendar.entity.User;
import com.sparta.springcommentcalendar.repository.ScheduleRepository;
import com.sparta.springcommentcalendar.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Schedule createSchedule(ScheduleDTO scheduleDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Schedule schedule = new Schedule();
        schedule.setTitle(scheduleDTO.getTitle());
        schedule.setContent(scheduleDTO.getContent());
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUser(user);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Transactional
    public Schedule updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        schedule.setTitle(scheduleDTO.getTitle());
        schedule.setContent(scheduleDTO.getContent());
        schedule.setUpdatedAt(LocalDateTime.now());
        return schedule;
    }

    @Transactional
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
