//package com.kumarmanglam.futuresync.controller;
//
//public class ReportController {
//}
package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.ReportDTO;
import com.kumarmanglam.futuresync.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{userId}")
    public ReportDTO getReport(
            @PathVariable Long userId
    ) {

        return reportService.generateReport(userId);
    }
}