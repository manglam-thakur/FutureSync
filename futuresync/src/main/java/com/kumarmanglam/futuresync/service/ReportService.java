//package com.kumarmanglam.futuresync.service;
//
//public class ReportService {
//}
package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.dto.ReportDTO;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.repository.ActivityRepository;
import com.kumarmanglam.futuresync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    public ReportDTO generateReport(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Activity> activities =
                activityRepository.findByUser_Id(userId);

        ReportDTO dto = new ReportDTO();

        if (activities.isEmpty()) {

            dto.setReportMessage(
                    "No activity data available."
            );

            return dto;
        }

        int totalStudyHours = 0;
        int totalTasks = 0;
        int totalDistraction = 0;
        int productiveDays = 0;
        int totalProductivity = 0;

        for (Activity activity : activities) {

            totalStudyHours += activity.getStudyHours();

            totalTasks += activity.getTasksCompleted();

            totalDistraction += activity.getDistractionTime();

            int productivity =
                    (activity.getStudyHours() * 2)
                            + (activity.getTasksCompleted() * 3)
                            - activity.getDistractionTime();

            totalProductivity += productivity;

            if (productivity >= 10) {
                productiveDays++;
            }
        }

        dto.setTotalStudyHours(totalStudyHours);

        dto.setTotalTasksCompleted(totalTasks);

        dto.setTotalDistractionTime(totalDistraction);

        dto.setAverageProductivity(
                (double) totalProductivity / activities.size()
        );

        dto.setProductiveDays(productiveDays);

        dto.setDisciplineScore(user.getDisciplineScore());

        // 🔥 AI-style summary
        if (dto.getAverageProductivity() >= 15) {

            dto.setReportMessage(
                    "Excellent performance this period."
            );

        } else if (dto.getAverageProductivity() >= 8) {

            dto.setReportMessage(
                    "Good progress, but consistency can improve."
            );

        } else {

            dto.setReportMessage(
                    "Performance needs improvement."
            );
        }

        return dto;
    }
}