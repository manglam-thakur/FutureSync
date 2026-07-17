//package com.kumarmanglam.futuresync.dto;
//
//public class ChartDataDTO {
//}
package com.kumarmanglam.futuresync.dto;

import java.time.LocalDate;

public class ChartDataDTO {

    private LocalDate date;

    private int studyHours;

    private int tasksCompleted;

    private int distractionTime;

    private int productivityScore;

    public ChartDataDTO() {
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

    public int getProductivityScore() {
        return productivityScore;
    }

    public void setProductivityScore(int productivityScore) {
        this.productivityScore = productivityScore;
    }
}