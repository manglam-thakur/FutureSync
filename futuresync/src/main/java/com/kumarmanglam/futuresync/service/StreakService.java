//package com.kumarmanglam.futuresync.service;
//
//public class StreakServices {
//}
package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.model.Streak;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.repository.StreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class StreakService {

    @Autowired
    private StreakRepository streakRepository;

    // ✅ Update streak automatically
    public void updateStreak(User user) {

        Streak streak = streakRepository.findByUser_Id(user.getId());

        // First time user
        if (streak == null) {

            streak = new Streak();
            streak.setUser(user);
            streak.setCurrentStreak(1);
            streak.setLongestStreak(1);
            streak.setLastActiveDate(LocalDate.now());

            streakRepository.save(streak);
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate lastDate = streak.getLastActiveDate();

        // Already updated today
        if (lastDate != null && lastDate.equals(today)) {
            return;
        }

        // Calculate day difference
        long daysBetween = ChronoUnit.DAYS.between(lastDate, today);

        // Consecutive day
        if (daysBetween == 1) {

            streak.setCurrentStreak(
                    streak.getCurrentStreak() + 1
            );

        } else {
            // streak broken
            streak.setCurrentStreak(1);
        }

        // Update longest streak
        if (streak.getCurrentStreak() > streak.getLongestStreak()) {

            streak.setLongestStreak(
                    streak.getCurrentStreak()
            );
        }

        streak.setLastActiveDate(today);

        streakRepository.save(streak);
    }

    // ✅ Get streak by user
    public Streak getUserStreak(Long userId) {
        return streakRepository.findByUser_Id(userId);
    }
}