package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.FocusDailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FocusDailyStatisticRepository extends JpaRepository<FocusDailyStatistic, Long> {

}
