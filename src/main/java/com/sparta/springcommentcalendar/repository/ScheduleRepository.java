package com.sparta.springcommentcalendar.repository;

import com.sparta.springcommentcalendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
