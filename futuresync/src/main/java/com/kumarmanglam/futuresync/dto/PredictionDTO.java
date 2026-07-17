//package com.kumarmanglam.futuresync.dto;
//
//public class PredictionDTO {
//}
package com.kumarmanglam.futuresync.dto;

public class PredictionDTO {

    private String futureProductivity;

    private String disciplineTrend;

    private String consistencyRisk;

    private int predictedScore;

    public PredictionDTO() {
    }

    public String getFutureProductivity() {
        return futureProductivity;
    }

    public void setFutureProductivity(String futureProductivity) {
        this.futureProductivity = futureProductivity;
    }

    public String getDisciplineTrend() {
        return disciplineTrend;
    }

    public void setDisciplineTrend(String disciplineTrend) {
        this.disciplineTrend = disciplineTrend;
    }

    public String getConsistencyRisk() {
        return consistencyRisk;
    }

    public void setConsistencyRisk(String consistencyRisk) {
        this.consistencyRisk = consistencyRisk;
    }

    public int getPredictedScore() {
        return predictedScore;
    }

    public void setPredictedScore(int predictedScore) {
        this.predictedScore = predictedScore;
    }
}