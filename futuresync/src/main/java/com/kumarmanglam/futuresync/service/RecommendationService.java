//package com.kumarmanglam.futuresync.service;
//
//public class RecommendationService {
//}
package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.dto.RecommendationDTO;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.repository.ActivityRepository;
import com.kumarmanglam.futuresync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PredictionService predictionService;

    public RecommendationDTO generateRecommendation(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Activity> activities =
                activityRepository.findByUser_Id(userId);

        RecommendationDTO dto = new RecommendationDTO();

        if (activities.isEmpty()) {

            dto.setPriority("HIGH");

            dto.setRecommendation(
                    "Start tracking your daily activities."
            );

            dto.setReason(
                    "No productivity data available."
            );

            return dto;
        }

        double avgStudy =
                activities.stream()
                        .mapToInt(Activity::getStudyHours)
                        .average()
                        .orElse(0);

        double avgDistraction =
                activities.stream()
                        .mapToInt(Activity::getDistractionTime)
                        .average()
                        .orElse(0);

        int streak =
                predictionService.calculateStreak(userId);

        // Recommendation Logic

        if (avgDistraction > 180) {

            dto.setPriority("HIGH");

            dto.setRecommendation(
                    "Reduce distraction time by at least 1 hour daily."
            );

            dto.setReason(
                    "Your distraction level is negatively affecting consistency."
            );
        }

        else if (avgStudy < 3) {

            dto.setPriority("MEDIUM");

            dto.setRecommendation(
                    "Increase study time gradually by 1 hour daily."
            );

            dto.setReason(
                    "Low study hours may slow long-term growth."
            );
        }

        else if (streak < 3) {

            dto.setPriority("MEDIUM");

            dto.setRecommendation(
                    "Focus on maintaining a daily productive streak."
            );

            dto.setReason(
                    "Consistency is currently unstable."
            );
        }

        else if (user.getDisciplineScore() < 50) {

            dto.setPriority("HIGH");

            dto.setRecommendation(
                    "Improve discipline by completing small commitments daily."
            );

            dto.setReason(
                    "Low discipline score indicates inconsistent habits."
            );
        }

        else {

            dto.setPriority("LOW");

            dto.setRecommendation(
                    "Maintain your current routine and continue improving."
            );

            dto.setReason(
                    "Your performance indicators are stable."
            );
        }

        return dto;
    }

}