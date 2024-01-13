package com.example.javatraining.schedule;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StudentSchedule {
    // show date-time after 10 seconds;
    @Scheduled(fixedDelay = 10000)
    public void showTime() {
        System.out.println("actually time is :" + LocalDateTime.now());
    }
}
