//package com.kumarmanglam.futuresync.controller;
//
//public class StreakController {
//}
package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.model.Streak;
import com.kumarmanglam.futuresync.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/streak")
public class StreakController {

    @Autowired
    private StreakService streakService;

    @GetMapping("/{userId}")
    public Streak getStreak(@PathVariable Long userId) {

        return streakService.getUserStreak(userId);
    }
}