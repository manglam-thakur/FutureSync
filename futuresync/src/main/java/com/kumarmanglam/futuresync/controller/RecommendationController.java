//package com.kumarmanglam.futuresync.controller;
//
//public class RecommendationController {
//}
package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.RecommendationDTO;
import com.kumarmanglam.futuresync.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public RecommendationDTO getRecommendation(
            @PathVariable Long userId
    ) {

        return recommendationService.generateRecommendation(userId);
    }
}