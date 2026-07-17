package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.DashboardResponse;
import com.kumarmanglam.futuresync.dto.FutureResponse;
import com.kumarmanglam.futuresync.dto.MotivationResponse;
import com.kumarmanglam.futuresync.dto.PredictionDTO;
import com.kumarmanglam.futuresync.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prediction")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    // ✅ Future Prediction
    @GetMapping("/future/{userId}")
    public FutureResponse predictFuture(
            @PathVariable Long userId
    ) {

        return predictionService.predictFuture(userId);
    }

    // ✅ Streak
    @GetMapping("/streak/{userId}")
    public int getStreak(
            @PathVariable Long userId
    ) {

        return predictionService.calculateStreak(userId);
    }

    // ✅ Dashboard
    @GetMapping("/dashboard/{userId}")
    public DashboardResponse getDashboard(
            @PathVariable Long userId
    ) {

        return predictionService.getDashboardStats(userId);
    }

    // ✅ Motivation
    @GetMapping("/motivation/{userId}")
    public MotivationResponse getMotivation(
            @PathVariable Long userId
    ) {

        return predictionService.getMotivation(userId);
    }

    // ✅ AI Prediction Metrics
    @GetMapping("/metrics/{userId}")
    public PredictionDTO getPredictionMetrics(
            @PathVariable Long userId
    ) {

        return predictionService.getPredictionMetrics(userId);
    }
}