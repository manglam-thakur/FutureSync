//package com.kumarmanglam.futuresync.service;
//
//public class PredictionService {
//}
package com.kumarmanglam.futuresync.service;
import com.kumarmanglam.futuresync.dto.MotivationResponse;
import com.kumarmanglam.futuresync.dto.FutureResponse;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.repository.ActivityRepository;
import com.kumarmanglam.futuresync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kumarmanglam.futuresync.dto.DashboardResponse;
import com.kumarmanglam.futuresync.dto.PredictionDTO;

import java.util.List;

@Service
public class PredictionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public FutureResponse predictFuture(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Activity> activities = activityRepository.findByUser_Id(userId);

        if (activities.isEmpty()) {
            return new FutureResponse(
                    "No Data Available",
                    "Start completing activities to unlock predictions.",
                    user.getDisciplineScore(),
                    0.0
            );
        }

        long completedCount = activities.stream()
                .filter(activity ->
                        activity.getStudyHours() >= 4 &&
                                activity.getTasksCompleted() >= 3 &&
                                activity.getDistractionTime() <= 120
                )
                .count();

        double completionRate =
                (double) completedCount / activities.size() * 100;

        String futureStatus;
        String advice;

        if (completionRate >= 80 && user.getDisciplineScore() >= 80) {
            futureStatus = "Excellent Future Ahead";
            advice = "Maintain consistency. You're building a remarkable life.";
        } else if (completionRate >= 60) {
            futureStatus = "Good Progress";
            advice = "Stay focused. Small daily improvements will compound.";
        } else if (completionRate >= 40) {
            futureStatus = "Needs Improvement";
            advice = "Reduce distractions and honor your commitments.";
        } else {
            futureStatus = "Warning Zone";
            advice = "Your current actions may harm your future. Act now.";
        }

        return new FutureResponse(
                futureStatus,
                advice,
                user.getDisciplineScore(),
                completionRate
        );
    }
    public int calculateStreak(Long userId) {

        List<Activity> activities =
                activityRepository.findByUser_IdOrderByDateAsc(userId);

        if (activities.isEmpty()) {
            return 0;
        }

        int streak = 0;

        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);

            boolean productive =
                    activity.getStudyHours() >= 4 &&
                            activity.getTasksCompleted() >= 3 &&
                            activity.getDistractionTime() <= 120;

            if (productive) {
                streak++;
            } else {
                break;
            }
        }

        return streak;
    }
    public DashboardResponse getDashboardStats(Long userId) {

        List<Activity> activities = activityRepository.findByUser_Id(userId);

        DashboardResponse response = new DashboardResponse();

        if (activities.isEmpty()) {
            return response;
        }

        int productiveDays = (int) activities.stream()
                .filter(activity ->
                        activity.getStudyHours() >= 4 &&
                                activity.getTasksCompleted() >= 3 &&
                                activity.getDistractionTime() <= 120)
                .count();

        response.setTotalCommitments(activities.size());
        response.setCompleted(productiveDays);
        response.setBroken(activities.size() - productiveDays);
        response.setProductiveDays(productiveDays);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        response.setDisciplineScore(user.getDisciplineScore());

        response.setAverageStudyHours(
                activities.stream()
                        .mapToInt(Activity::getStudyHours)
                        .average()
                        .orElse(0)
        );

        response.setAverageTasksCompleted(
                activities.stream()
                        .mapToInt(Activity::getTasksCompleted)
                        .average()
                        .orElse(0)
        );

        response.setAverageDistractionTime(
                activities.stream()
                        .mapToInt(Activity::getDistractionTime)
                        .average()
                        .orElse(0)
        );

        return response;
    }
    public MotivationResponse getMotivation(Long userId) {

        FutureResponse future = predictFuture(userId);

        String message;
        String category;

        switch (future.getFutureStatus()) {

            case "Excellent Future Ahead":
                category = "SUCCESS";
                message = "You are unstoppable. Your habits are shaping an extraordinary future.";
                break;

            case "Good Progress":
                category = "PROGRESS";
                message = "You are on the right path. Keep moving forward every single day.";
                break;

            case "Needs Improvement":
                category = "WARNING";
                message = "Your future depends on today's discipline. Improve one habit today.";
                break;

            default:
                category = "ALERT";
                message = "The best time to change your life is now. Start today.";
        }

        return new MotivationResponse(message, category);
    }
    public PredictionDTO getPredictionMetrics(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Activity> activities =
                activityRepository.findByUser_Id(userId);

        PredictionDTO dto = new PredictionDTO();

        if (activities.isEmpty()) {

            dto.setFutureProductivity("LOW");
            dto.setDisciplineTrend("UNKNOWN");
            dto.setConsistencyRisk("HIGH");
            dto.setPredictedScore(0);

            return dto;
        }

        int totalScore = 0;

        for (Activity activity : activities) {

            int score =
                    (activity.getStudyHours() * 2)
                            + (activity.getTasksCompleted() * 3)
                            - activity.getDistractionTime();

            totalScore += score;
        }

        int avgScore = totalScore / activities.size();

        dto.setPredictedScore(avgScore);

        // Productivity
        if (avgScore >= 50) {
            dto.setFutureProductivity("HIGH");
        }
        else if (avgScore >= 25) {
            dto.setFutureProductivity("MEDIUM");
        }
        else {
            dto.setFutureProductivity("LOW");
        }

        // Discipline Trend
        if (user.getDisciplineScore() >= 75) {
            dto.setDisciplineTrend("IMPROVING");
        }
        else if (user.getDisciplineScore() >= 40) {
            dto.setDisciplineTrend("STABLE");
        }
        else {
            dto.setDisciplineTrend("DECLINING");
        }

        // Consistency Risk
        int streak = calculateStreak(userId);

        if (streak >= 7) {
            dto.setConsistencyRisk("LOW");
        }
        else if (streak >= 3) {
            dto.setConsistencyRisk("MEDIUM");
        }
        else {
            dto.setConsistencyRisk("HIGH");
        }

        return dto;
    }

}