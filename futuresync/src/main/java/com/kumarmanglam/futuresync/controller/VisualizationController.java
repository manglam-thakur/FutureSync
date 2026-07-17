//package com.kumarmanglam.futuresync.controller;
//
//public class VisualizationController {
//}
package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.ChartDataDTO;
import com.kumarmanglam.futuresync.service.VisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kumarmanglam.futuresync.dto.TrendDTO;

import java.util.List;

@RestController
@RequestMapping("/visualization")
public class VisualizationController {

    @Autowired
    private VisualizationService visualizationService;

    // ✅ Full graph data
    @GetMapping("/{userId}")
    public List<ChartDataDTO> getChartData(
            @PathVariable Long userId
    ) {

        return visualizationService.getChartData(userId);
    }

    // ✅ Weekly graph data
    @GetMapping("/weekly/{userId}")
    public List<ChartDataDTO> getWeeklyData(
            @PathVariable Long userId
    ) {

        return visualizationService.getWeeklyChartData(userId);
    }
    @GetMapping("/monthly/{userId}")
    public List<ChartDataDTO> getMonthlyData(
            @PathVariable Long userId
    ) {

        return visualizationService.getMonthlyChartData(userId);
    }
    @GetMapping("/trend/{userId}")
    public TrendDTO getTrend(
            @PathVariable Long userId
    ) {

        return visualizationService.getProductivityTrend(userId);
    }

}
