package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.model.Violation;
import com.kumarmanglam.futuresync.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/violation")
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    // ✅ Add violation with reason
    @PostMapping("/add/{commitmentId}")
    public Violation addViolation(@PathVariable Long commitmentId,
                                  @RequestParam String reason) {
        return violationService.addViolation(commitmentId, reason);
    }

    // ✅ NEW: Get all violations of a user
    @GetMapping("/user/{userId}")
    public List<Violation> getViolations(@PathVariable Long userId) {
        return violationService.getViolationsByUser(userId);
    }
}