package com.sparta.springcommentcalendar.controller;

import com.sparta.springcommentcalendar.dto.ScheduleDTO;
import com.sparta.springcommentcalendar.entity.Schedule;
import com.sparta.springcommentcalendar.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDTO scheduleDTO, @RequestHeader("Authorization") String token) {
        String username = extractUsernameFromToken(token);
        Schedule schedule = scheduleService.createSchedule(scheduleDTO, username);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }

    private String extractUsernameFromToken(String token) {
        return token.startsWith("Bearer ") ? token.substring(7) : token;
    }
}
