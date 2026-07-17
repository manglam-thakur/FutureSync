package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.model.Commitment;
import com.kumarmanglam.futuresync.service.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commitment")
@CrossOrigin("*") // optional but helpful
public class CommitmentController {

    @Autowired
    private CommitmentService commitmentService;

    // ✅ Create commitment
    @PostMapping("/add")
    public Commitment create(@RequestBody Commitment commitment) {
        return commitmentService.createCommitment(commitment);
    }

    // ✅ Break commitment
    @PutMapping("/break/{commitmentId}")
    public Commitment breakCommitment(@PathVariable Long commitmentId) {
        return commitmentService.breakCommitment(commitmentId);
    }

    // ✅ Complete commitment
    @PutMapping("/complete/{commitmentId}")
    public Commitment completeCommitment(@PathVariable Long commitmentId) {
        return commitmentService.completeCommitment(commitmentId);
    }

    // ✅ Get all commitments of user
    @GetMapping("/user/{userId}")
    public List<Commitment> getAll(@PathVariable Long userId) {
        return commitmentService.getAllCommitments(userId);
    }
}