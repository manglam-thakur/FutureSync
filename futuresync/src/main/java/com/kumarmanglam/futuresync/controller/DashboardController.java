package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.DashboardDTO;
import com.kumarmanglam.futuresync.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{userId}")
    public DashboardDTO getDashboard(
            @PathVariable Long userId) {

        return dashboardService.getDashboard(userId);
    }
}