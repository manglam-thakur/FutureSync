package com.kumarmanglam.futuresync.dto;

public class DashboardResponse {

    // Existing Fields
    private int totalCommitments;
    private int completed;
    private int broken;
    private int disciplineScore;

    // New Analytics Fields
    private double averageStudyHours;
    private double averageTasksCompleted;
    private double averageDistractionTime;
    private int productiveDays;

    // Getters and Setters

    public int getTotalCommitments() {
        return totalCommitments;
    }

    public void setTotalCommitments(int totalCommitments) {
        this.totalCommitments = totalCommitments;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getBroken() {
        return broken;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }

    public int getDisciplineScore() {
        return disciplineScore;
    }

    public void setDisciplineScore(int disciplineScore) {
        this.disciplineScore = disciplineScore;
    }

    public double getAverageStudyHours() {
        return averageStudyHours;
    }

    public void setAverageStudyHours(double averageStudyHours) {
        this.averageStudyHours = averageStudyHours;
    }

    public double getAverageTasksCompleted() {
        return averageTasksCompleted;
    }

    public void setAverageTasksCompleted(double averageTasksCompleted) {
        this.averageTasksCompleted = averageTasksCompleted;
    }

    public double getAverageDistractionTime() {
        return averageDistractionTime;
    }

    public void setAverageDistractionTime(double averageDistractionTime) {
        this.averageDistractionTime = averageDistractionTime;
    }

    public int getProductiveDays() {
        return productiveDays;
    }

    public void setProductiveDays(int productiveDays) {
        this.productiveDays = productiveDays;
    }
}