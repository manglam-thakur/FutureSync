package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.dto.ActivityDTO;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.repository.ActivityRepository;
import com.kumarmanglam.futuresync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitmentService commitmentService;

    @Autowired
    private StreakService streakService;

    // ✅ Add Activity
    public Activity addActivity(Activity activity) {

        if (activity.getUser() == null || activity.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        User user = userRepository.findById(activity.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ No null checks needed (primitive int)
        activity.setUser(user);
        activity.setDate(LocalDate.now());

        Activity savedActivity = activityRepository.save(activity);

        // 🔥 Productivity Score
        int score = calculateProductivityScore(savedActivity);

        // 🔥 Update Discipline
        updateDiscipline(user, score);

        streakService.updateStreak(user);
        // 🔥 Evaluate Commitments
        commitmentService.evaluateCommitments(
                user,
                savedActivity.getStudyHours(),
                savedActivity.getTasksCompleted()
        );

        return savedActivity;
    }

    // ✅ Discipline Logic (clean + safe)
    private void updateDiscipline(User user, int score) {

        int current = user.getDisciplineScore();

        if (score >= 60) {
            current += 5;
        } else if (score >= 30) {
            current += 2;
        } else if (score < 0) {
            current -= 2;
        }
        // else → no change

        // Clamp between 0–100
        current = Math.max(0, Math.min(100, current));

        user.setDisciplineScore(current);
        userRepository.save(user);
    }

    // ✅ Productivity Score
    public int calculateProductivityScore(Activity activity) {

        int study = activity.getStudyHours();
        int tasks = activity.getTasksCompleted();
        int distraction = activity.getDistractionTime();

        return (study * 2 + tasks * 3) - distraction;
    }

    // ✅ Get User Activity History
    public List<ActivityDTO> getUserHistory(Long userId) {

        List<Activity> activities = activityRepository.findByUser_Id(userId);

        return activities.stream().map(activity -> {
            ActivityDTO dto = new ActivityDTO();
            dto.setId(activity.getId());
            dto.setDate(activity.getDate());
            dto.setStudyHours(activity.getStudyHours());
            dto.setTasksCompleted(activity.getTasksCompleted());
            dto.setDistractionTime(activity.getDistractionTime());
            dto.setUserId(activity.getUser().getId());
            dto.setUserName(activity.getUser().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    // ✅ Get Activity By ID
    public Activity getById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }
}