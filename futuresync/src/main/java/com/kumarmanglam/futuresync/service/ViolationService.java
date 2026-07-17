package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.model.Commitment;
import com.kumarmanglam.futuresync.model.User;
import com.kumarmanglam.futuresync.model.Violation;
import com.kumarmanglam.futuresync.repository.CommitmentRepository;
import com.kumarmanglam.futuresync.repository.UserRepository;
import com.kumarmanglam.futuresync.repository.ViolationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViolationService {

    @Autowired
    private ViolationRepository violationRepository;

    @Autowired
    private CommitmentRepository commitmentRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Add violation with reason
    public Violation addViolation(Long commitmentId, String reason) {

        Commitment c = commitmentRepository.findById(commitmentId)
                .orElseThrow(() -> new RuntimeException("Commitment not found"));

        // Create violation
        Violation v = new Violation();
        v.setReason(reason);
        v.setTimestamp(LocalDateTime.now());
        v.setCommitment(c);

        // Deduct discipline score
        User u = c.getUser();
        u.setDisciplineScore(u.getDisciplineScore() - 10);
        userRepository.save(u);

        return violationRepository.save(v);
    }

    // ✅ NEW: Get all violations of a user
    public List<Violation> getViolationsByUser(Long userId) {
        return violationRepository.findByCommitment_User_Id(userId);
    }
}