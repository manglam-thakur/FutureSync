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
public class CommitmentService {

    @Autowired
    private CommitmentRepository commitmentRepository;

    @Autowired
    private ViolationRepository violationRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Create a new commitment
    public Commitment createCommitment(Commitment commitment) {

        if (commitment.getUser() == null || commitment.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        User user = userRepository.findById(commitment.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        commitment.setUser(user);
        commitment.setStatus(Commitment.CommitmentStatus.ACTIVE);

        return commitmentRepository.save(commitment);
    }

    // ✅ Break a commitment
    public Commitment breakCommitment(Long commitmentId) {

        Commitment c = commitmentRepository.findById(commitmentId)
                .orElseThrow(() -> new RuntimeException("Commitment not found"));

        if (c.getStatus() == Commitment.CommitmentStatus.ACTIVE) {

            c.setStatus(Commitment.CommitmentStatus.BROKEN);

            // 🔥 Log violation
            Violation v = new Violation();
            v.setCommitment(c);
            v.setTimestamp(LocalDateTime.now());
            v.setReason("Commitment not fulfilled");
            violationRepository.save(v);

            // 🔥 Deduct discipline score (min 0)
            User u = c.getUser();
            u.setDisciplineScore(Math.max(0, u.getDisciplineScore() - 15));
            userRepository.save(u);

            return commitmentRepository.save(c);
        }

        return c;
    }

    // ✅ Complete a commitment
    public Commitment completeCommitment(Long commitmentId) {

        Commitment c = commitmentRepository.findById(commitmentId)
                .orElseThrow(() -> new RuntimeException("Commitment not found"));

        if (c.getStatus() == Commitment.CommitmentStatus.ACTIVE) {

            c.setStatus(Commitment.CommitmentStatus.COMPLETED);

            // 🔥 Increase discipline score (max 100)
            User u = c.getUser();
            u.setDisciplineScore(Math.min(100, u.getDisciplineScore() + 10));
            userRepository.save(u);

            return commitmentRepository.save(c);
        }

        return c;
    }

    // ✅ Get all commitments of a user
    public List<Commitment> getAllCommitments(Long userId) {
        return commitmentRepository.findByUser_Id(userId);
    }

    // 🔥 AUTO EVALUATION ENGINE (CORE FEATURE)
    public void evaluateCommitments(User user, int studyHours, int tasksCompleted) {

        List<Commitment> commitments =
                commitmentRepository.findByUser_Id(user.getId());

        for (Commitment c : commitments) {

            if (c.getStatus() != Commitment.CommitmentStatus.ACTIVE) {
                continue;
            }

            // ⏱ Only evaluate AFTER end time
            if (c.getEndTime() == null || c.getEndTime().isAfter(LocalDateTime.now())) {
                continue;
            }

            boolean completed = true;

            // ✅ Study hours check
            if (c.getTargetStudyHours() > 0 &&
                    studyHours < c.getTargetStudyHours()) {
                completed = false;
            }

            // ✅ Tasks check
            if (c.getTargetTasks() > 0 &&
                    tasksCompleted < c.getTargetTasks()) {
                completed = false;
            }

            // 🎯 Final decision
            if (completed) {
                completeCommitment(c.getId());
            } else {
                breakCommitment(c.getId());
            }
        }
    }
}