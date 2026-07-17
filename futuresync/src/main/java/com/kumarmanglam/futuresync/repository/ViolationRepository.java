package com.kumarmanglam.futuresync.repository;

import com.kumarmanglam.futuresync.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Long> {

    // ✅ Get all violations of a specific user
    List<Violation> findByCommitment_User_Id(Long userId);

}