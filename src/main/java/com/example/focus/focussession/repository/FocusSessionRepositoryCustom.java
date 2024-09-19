package com.example.focus.focussession.repository;

import com.example.focus.focussession.dto.CumulativeTimeDto;
import com.example.focus.focussession.dto.CumulativeWeekTimeDto;

import java.util.List;

public interface FocusSessionRepositoryCustom {

    List<CumulativeTimeDto> findCumulativeTimeByDate();

    List<CumulativeTimeDto> findCumulativeTimeByDateAndMemberId(Long memberId);

    List<CumulativeWeekTimeDto> findCumulativeTimeByWeekAndMemberId(Long memberId);
}
