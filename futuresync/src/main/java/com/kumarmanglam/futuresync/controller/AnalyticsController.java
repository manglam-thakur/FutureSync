package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.AnalyticsDTO;
import com.kumarmanglam.futuresync.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/summary/{userId}")
    public AnalyticsDTO getSummary(@PathVariable Long userId) {
        return analyticsService.getUserAnalytics(userId);
    }
}