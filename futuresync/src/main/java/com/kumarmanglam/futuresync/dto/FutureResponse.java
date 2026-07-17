//package com.kumarmanglam.futuresync.dto;
//
//public class FutureResponse {
//}
package com.kumarmanglam.futuresync.dto;

public class FutureResponse {

    private String futureStatus;
    private String advice;
    private int disciplineScore;
    private double completionRate;

    public FutureResponse() {
    }

    public FutureResponse(String futureStatus, String advice,
                          int disciplineScore, double completionRate) {
        this.futureStatus = futureStatus;
        this.advice = advice;
        this.disciplineScore = disciplineScore;
        this.completionRate = completionRate;
    }

    public String getFutureStatus() {
        return futureStatus;
    }

    public void setFutureStatus(String futureStatus) {
        this.futureStatus = futureStatus;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getDisciplineScore() {
        return disciplineScore;
    }

    public void setDisciplineScore(int disciplineScore) {
        this.disciplineScore = disciplineScore;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }
}