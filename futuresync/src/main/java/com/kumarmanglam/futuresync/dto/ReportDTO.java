//package com.kumarmanglam.futuresync.dto;
//
//public class ReportDTO {
//}
package com.kumarmanglam.futuresync.dto;

public class ReportDTO {

    private int totalStudyHours;

    private int totalTasksCompleted;

    private int totalDistractionTime;

    private double averageProductivity;

    private int productiveDays;

    private int disciplineScore;

    private String reportMessage;

    public ReportDTO() {
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

    public double getAverageProductivity() {
        return averageProductivity;
    }

    public void setAverageProductivity(double averageProductivity) {
        this.averageProductivity = averageProductivity;
    }

    public int getProductiveDays() {
        return productiveDays;
    }

    public void setProductiveDays(int productiveDays) {
        this.productiveDays = productiveDays;
    }

    public int getDisciplineScore() {
        return disciplineScore;
    }

    public void setDisciplineScore(int disciplineScore) {
        this.disciplineScore = disciplineScore;
    }

    public String getReportMessage() {
        return reportMessage;
    }

    public void setReportMessage(String reportMessage) {
        this.reportMessage = reportMessage;
    }
}