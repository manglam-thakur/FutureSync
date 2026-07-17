package com.kumarmanglam.futuresync.repository;

import com.kumarmanglam.futuresync.model.Commitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommitmentRepository extends JpaRepository<Commitment, Long> {

    // Get all commitments of a user
    List<Commitment> findByUser_Id(Long userId);

    // 🔥 Get only ACTIVE commitments (IMPORTANT)
    List<Commitment> findByUser_IdAndStatus(Long userId, Commitment.CommitmentStatus status);

    // Count total commitments
    int countByUser_Id(Long userId);

    // Count by status (COMPLETED / BROKEN / ACTIVE)
    int countByUser_IdAndStatus(Long userId, Commitment.CommitmentStatus status);
}