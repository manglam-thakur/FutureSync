//package com.kumarmanglam.futuresync.dto;
//
//public class TrendDTO {
//}
package com.kumarmanglam.futuresync.dto;

public class TrendDTO {

    private double previousAverage;

    private double currentAverage;

    private String trend;

    public TrendDTO() {
    }

    public double getPreviousAverage() {
        return previousAverage;
    }

    public void setPreviousAverage(double previousAverage) {
        this.previousAverage = previousAverage;
    }

    public double getCurrentAverage() {
        return currentAverage;
    }

    public void setCurrentAverage(double currentAverage) {
        this.currentAverage = currentAverage;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}