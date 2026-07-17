//package com.kumarmanglam.futuresync.dto;
//
//public class RecommendationDTO {
//}
package com.kumarmanglam.futuresync.dto;

public class RecommendationDTO {

    private String priority;

    private String recommendation;

    private String reason;

    public RecommendationDTO() {
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}