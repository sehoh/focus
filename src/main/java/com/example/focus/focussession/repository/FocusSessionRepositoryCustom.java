package com.example.focus.focussession.repository;

import com.example.focus.focussession.dto.DailyCumulativeTime;
import com.example.focus.focussession.dto.MonthlyCumulativeTime;
import com.example.focus.focussession.dto.WeeklyCumulativeTime;

import java.util.List;

public interface FocusSessionRepositoryCustom {

    List<DailyCumulativeTime> findCumulativeTimeByDate();

    List<DailyCumulativeTime> findCumulativeTimeByDateAndMemberId(Long memberId);

    List<WeeklyCumulativeTime> findCumulativeTimeByWeekAndMemberId(Long memberId);

    List<MonthlyCumulativeTime> findCumulativeTimeByMonthAndMemberId(Long memberId);
}
