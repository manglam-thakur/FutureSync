package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.ActivityDTO;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // ✅ Add activity
    @PostMapping("/add")
    public Activity addActivity(@RequestBody Activity activity) {
        return activityService.addActivity(activity);
    }

    // ✅ Get activity history for a user (returns DTO to avoid null fields)
    @GetMapping("/history/{userId}")
    public List<ActivityDTO> getHistory(@PathVariable Long userId) {
        return activityService.getUserHistory(userId);
    }

    // ✅ Get productivity score of a single activity
    @GetMapping("/score/{activityId}")
    public int getScore(@PathVariable Long activityId) {
        Activity activity = activityService.getById(activityId);
        if (activity == null) {
            return 0; // or throw exception if you prefer
        }
        return activityService.calculateProductivityScore(activity);
    }
}