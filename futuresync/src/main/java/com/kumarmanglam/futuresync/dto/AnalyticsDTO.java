package com.kumarmanglam.futuresync.dto;

public class AnalyticsDTO {

    private int totalStudyHours;
    private int totalTasksCompleted;
    private int totalDistractionTime;
    private double averageProductivityScore;
    private int totalActivities;

    public AnalyticsDTO() {
    }

    public int getTotalStudyHours() {
        return totalStudyHours;
    }

    public void setTotalStudyHours(int totalStudyHours) {
        this.totalStudyHours = totalStudyHours;
    }

    public int getTotalTasksCompleted() {
        return totalTasksCompleted;
    }

    public void setTotalTasksCompleted(int totalTasksCompleted) {
        this.totalTasksCompleted = totalTasksCompleted;
    }

    public int getTotalDistractionTime() {
        return totalDistractionTime;
    }

    public void setTotalDistractionTime(int totalDistractionTime) {
        this.totalDistractionTime = totalDistractionTime;
    }

    public double getAverageProductivityScore() {
        return averageProductivityScore;
    }

    public void setAverageProductivityScore(double averageProductivityScore) {
        this.averageProductivityScore = averageProductivityScore;
    }

    public int getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(int totalActivities) {
        this.totalActivities = totalActivities;
    }
}