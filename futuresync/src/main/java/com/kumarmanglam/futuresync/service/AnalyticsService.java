package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.dto.AnalyticsDTO;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private ActivityRepository activityRepository;

    public AnalyticsDTO getUserAnalytics(Long userId) {

        List<Activity> activities =
                activityRepository.findByUser_Id(userId);

        AnalyticsDTO dto = new AnalyticsDTO();

        int totalStudyHours = 0;
        int totalTasksCompleted = 0;
        int totalDistractionTime = 0;
        int totalProductivity = 0;

        for (Activity activity : activities) {

            totalStudyHours += activity.getStudyHours();
            totalTasksCompleted += activity.getTasksCompleted();
            totalDistractionTime += activity.getDistractionTime();

            int productivity =
                    (activity.getStudyHours() * 2)
                            + (activity.getTasksCompleted() * 3)
                            - activity.getDistractionTime();

            totalProductivity += productivity;
        }

        dto.setTotalStudyHours(totalStudyHours);
        dto.setTotalTasksCompleted(totalTasksCompleted);
        dto.setTotalDistractionTime(totalDistractionTime);
        dto.setTotalActivities(activities.size());

        if (!activities.isEmpty()) {
            dto.setAverageProductivityScore(
                    (double) totalProductivity / activities.size()
            );
        } else {
            dto.setAverageProductivityScore(0.0);
        }

        return dto;
    }
}