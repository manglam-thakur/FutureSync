//package com.kumarmanglam.futuresync.repository;
//
//public class StreakRepository {
//}
package com.kumarmanglam.futuresync.repository;

import com.kumarmanglam.futuresync.model.Streak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreakRepository extends JpaRepository<Streak, Long> {

    Streak findByUser_Id(Long userId);
}