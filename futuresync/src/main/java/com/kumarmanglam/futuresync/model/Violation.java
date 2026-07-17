package com.kumarmanglam.futuresync.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason; // ✅ NEW FIELD

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "commitment_id")
    @JsonIgnoreProperties({"user"}) // prevents recursion
    private Commitment commitment;

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    // (optional but good practice)
    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Commitment getCommitment() {
        return commitment;
    }

    public void setCommitment(Commitment commitment) {
        this.commitment = commitment;
    }
}