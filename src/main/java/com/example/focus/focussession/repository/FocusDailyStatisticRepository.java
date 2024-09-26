package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.FocusDailyStatistic;
import com.example.focus.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FocusDailyStatisticRepository extends JpaRepository<FocusDailyStatistic, Long> {

    // member, date로 focusDailyStatistic 가져오기
    Optional<FocusDailyStatistic> findFocusDailyStatisticByDateAndMember(LocalDate date, Member member);

}
