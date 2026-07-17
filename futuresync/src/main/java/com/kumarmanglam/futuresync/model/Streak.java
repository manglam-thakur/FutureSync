//package com.kumarmanglam.futuresync.model;
//
//public class Streak {
//}
package com.kumarmanglam.futuresync.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "streaks")
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int currentStreak = 0;

    private int longestStreak = 0;

    private LocalDate lastActiveDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Streak() {
    }

    public Long getId() {
        return id;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public LocalDate getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(LocalDate lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}