package com.kumarmanglam.futuresync.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Commitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 🔥 NEW → measurable targets
    private int targetStudyHours;
    private int targetTasks;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private CommitmentStatus status = CommitmentStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"commitments"})
    private User user;

    // Enum for commitment status
    public enum CommitmentStatus {
        ACTIVE,
        COMPLETED,
        BROKEN
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // ✅ already fixed
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // 🔥 NEW GETTERS/SETTERS

    public int getTargetStudyHours() {
        return targetStudyHours;
    }

    public void setTargetStudyHours(int targetStudyHours) {
        this.targetStudyHours = targetStudyHours;
    }

    public int getTargetTasks() {
        return targetTasks;
    }

    public void setTargetTasks(int targetTasks) {
        this.targetTasks = targetTasks;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public CommitmentStatus getStatus() {
        return status;
    }

    public void setStatus(CommitmentStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}