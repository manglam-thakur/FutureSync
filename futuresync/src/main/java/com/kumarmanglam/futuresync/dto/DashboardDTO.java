//package com.kumarmanglam.futuresync.dto;
//
//public class DashboardDTO {
//}
package com.kumarmanglam.futuresync.dto;

public class DashboardDTO {

    private int disciplineScore;

    private int currentStreak;

    private int totalStudyHours;

    private int totalTasksCompleted;

    private double averageProductivity;

    private String futureStatus;

    private String recommendation;

    private String trend;

    public DashboardDTO() {
    }

    public int getDisciplineScore() {
        return disciplineScore;
    }

    public void setDisciplineScore(int disciplineScore) {
        this.disciplineScore = disciplineScore;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
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

    public double getAverageProductivity() {
        return averageProductivity;
    }

    public void setAverageProductivity(double averageProductivity) {
        this.averageProductivity = averageProductivity;
    }

    public String getFutureStatus() {
        return futureStatus;
    }

    public void setFutureStatus(String futureStatus) {
        this.futureStatus = futureStatus;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}