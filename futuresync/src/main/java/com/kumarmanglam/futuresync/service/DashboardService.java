package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.dto.*;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private VisualizationService visualizationService;

    public DashboardDTO getDashboard(Long userId) {

        DashboardDTO dto = new DashboardDTO();

        // ✅ USER
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ ANALYTICS
        AnalyticsDTO analytics =
                analyticsService.getUserAnalytics(userId);

        // ✅ PREDICTION
        FutureResponse future =
                predictionService.predictFuture(userId);

        // ✅ RECOMMENDATION
        RecommendationDTO recommendation =
                recommendationService.generateRecommendation(userId);

        // ✅ TREND
        TrendDTO trend =
                visualizationService.getTrend(userId);

        // ✅ STREAK
        int streak =
                predictionService.calculateStreak(userId);

        // =========================
        // SET DATA
        // =========================

        dto.setDisciplineScore(user.getDisciplineScore());

        dto.setCurrentStreak(streak);

        dto.setTotalStudyHours(
                analytics.getTotalStudyHours());

        dto.setTotalTasksCompleted(
                analytics.getTotalTasksCompleted());

        dto.setAverageProductivity(
                analytics.getAverageProductivityScore());

        dto.setFutureStatus(
                future.getFutureStatus());

        dto.setRecommendation(
                recommendation.getRecommendation());

        dto.setTrend(
                trend.getTrend());

        return dto;
    }
}