//package com.kumarmanglam.futuresync.service;
//
//public class VisualizationService {
//}
package com.kumarmanglam.futuresync.service;

import com.kumarmanglam.futuresync.dto.ChartDataDTO;
import com.kumarmanglam.futuresync.model.Activity;
import com.kumarmanglam.futuresync.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kumarmanglam.futuresync.dto.TrendDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisualizationService {

    @Autowired
    private ActivityRepository activityRepository;

    // ✅ Full activity chart data
    public List<ChartDataDTO> getChartData(Long userId) {

        List<Activity> activities =
                activityRepository.findByUser_IdOrderByDateAsc(userId);

        List<ChartDataDTO> response = new ArrayList<>();

        for (Activity activity : activities) {

            ChartDataDTO dto = new ChartDataDTO();

            dto.setDate(activity.getDate());

            dto.setStudyHours(activity.getStudyHours());

            dto.setTasksCompleted(activity.getTasksCompleted());

            dto.setDistractionTime(activity.getDistractionTime());

            int productivity =
                    (activity.getStudyHours() * 2)
                            + (activity.getTasksCompleted() * 3)
                            - activity.getDistractionTime();

            dto.setProductivityScore(productivity);

            response.add(dto);
        }

        return response;
    }

    // ✅ Weekly chart data
    public List<ChartDataDTO> getWeeklyChartData(Long userId) {

        LocalDate endDate = LocalDate.now();

        LocalDate startDate = endDate.minusDays(6);

        List<Activity> activities =
                activityRepository
                        .findByUser_IdAndDateBetweenOrderByDateAsc(
                                userId,
                                startDate,
                                endDate
                        );

        List<ChartDataDTO> response = new ArrayList<>();

        for (Activity activity : activities) {

            ChartDataDTO dto = new ChartDataDTO();

            dto.setDate(activity.getDate());

            dto.setStudyHours(activity.getStudyHours());

            dto.setTasksCompleted(activity.getTasksCompleted());

            dto.setDistractionTime(activity.getDistractionTime());

            int productivity =
                    (activity.getStudyHours() * 2)
                            + (activity.getTasksCompleted() * 3)
                            - activity.getDistractionTime();

            dto.setProductivityScore(productivity);

            response.add(dto);
        }

        return response;
    }
    public List<ChartDataDTO> getMonthlyChartData(Long userId) {

        LocalDate endDate = LocalDate.now();

        LocalDate startDate = endDate.minusDays(29);

        List<Activity> activities =
                activityRepository
                        .findByUser_IdAndDateBetweenOrderByDateAsc(
                                userId,
                                startDate,
                                endDate
                        );

        List<ChartDataDTO> response = new ArrayList<>();

        for (Activity activity : activities) {

            ChartDataDTO dto = new ChartDataDTO();

            dto.setDate(activity.getDate());

            dto.setStudyHours(activity.getStudyHours());

            dto.setTasksCompleted(activity.getTasksCompleted());

            dto.setDistractionTime(activity.getDistractionTime());

            int productivity =
                    (activity.getStudyHours() * 2)
                            + (activity.getTasksCompleted() * 3)
                            - activity.getDistractionTime();

            dto.setProductivityScore(productivity);

            response.add(dto);
        }

        return response;
    }
    public TrendDTO getProductivityTrend(Long userId) {

        List<Activity> activities =
                activityRepository.findByUser_IdOrderByDateAsc(userId);

        TrendDTO dto = new TrendDTO();

        if (activities.size() < 2) {

            dto.setPreviousAverage(0);
            dto.setCurrentAverage(0);
            dto.setTrend("Not Enough Data");

            return dto;
        }

        int mid = activities.size() / 2;

        List<Activity> firstHalf =
                activities.subList(0, mid);

        List<Activity> secondHalf =
                activities.subList(mid, activities.size());

        double previousAvg =
                firstHalf.stream()
                        .mapToInt(activity ->
                                (activity.getStudyHours() * 2)
                                        + (activity.getTasksCompleted() * 3)
                                        - activity.getDistractionTime()
                        )
                        .average()
                        .orElse(0);

        double currentAvg =
                secondHalf.stream()
                        .mapToInt(activity ->
                                (activity.getStudyHours() * 2)
                                        + (activity.getTasksCompleted() * 3)
                                        - activity.getDistractionTime()
                        )
                        .average()
                        .orElse(0);

        dto.setPreviousAverage(previousAvg);

        dto.setCurrentAverage(currentAvg);

        if (currentAvg > previousAvg) {
            dto.setTrend("Improving");
        }
        else if (currentAvg < previousAvg) {
            dto.setTrend("Declining");
        }
        else {
            dto.setTrend("Stable");
        }

        return dto;
    }
    public TrendDTO getTrend(Long userId) {

        List<Activity> activities =
                activityRepository.findByUser_Id(userId);

        TrendDTO dto = new TrendDTO();

        if (activities.size() < 2) {

            dto.setPreviousAverage(0);
            dto.setCurrentAverage(0);
            dto.setTrend("Not Enough Data");

            return dto;
        }

        int mid = activities.size() / 2;

        double previous = activities.subList(0, mid)
                .stream()
                .mapToInt(Activity::getProductivityScore)
                .average()
                .orElse(0);

        double current = activities.subList(mid, activities.size())
                .stream()
                .mapToInt(Activity::getProductivityScore)
                .average()
                .orElse(0);

        dto.setPreviousAverage(previous);
        dto.setCurrentAverage(current);

        if (current > previous) {
            dto.setTrend("Improving");
        }

        else if (current < previous) {
            dto.setTrend("Declining");
        }

        else {
            dto.setTrend("Stable");
        }

        return dto;
    }
}