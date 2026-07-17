package com.kumarmanglam.futuresync.repository;

import com.kumarmanglam.futuresync.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUser_Id(Long userId);

    List<Activity> findByUser_IdOrderByDateAsc(Long userId);

    List<Activity> findByUser_IdAndDate(
            Long userId,
            LocalDate date
    );

    List<Activity> findByUser_IdAndDateBetweenOrderByDateAsc(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );
}