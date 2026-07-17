//package com.kumarmanglam.futuresync.dto;
//
//public class ActivityDTO {
//}
package com.kumarmanglam.futuresync.dto;

import java.time.LocalDate;

public class ActivityDTO {

    private Long id;
    private LocalDate date;
    private int studyHours;
    private int tasksCompleted;
    private int distractionTime;
    private Long userId;
    private String userName;  // optional: to show name in response

    // Getters & Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getStudyHours() {
        return studyHours;
    }
    public void setStudyHours(int studyHours) {
        this.studyHours = studyHours;
    }
    public int getTasksCompleted() {
        return tasksCompleted;
    }
    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }
    public int getDistractionTime() {
        return distractionTime;
    }
    public void setDistractionTime(int distractionTime) {
        this.distractionTime = distractionTime;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}