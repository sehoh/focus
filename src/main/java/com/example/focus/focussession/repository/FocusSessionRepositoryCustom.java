package com.example.focus.focussession.repository;

import com.example.focus.focussession.dto.CumulativeTimeDto;

import java.util.List;

public interface FocusSessionRepositoryCustom {

    List<CumulativeTimeDto> findCumulativeTimeByDate();

    List<CumulativeTimeDto> findCumulativeTimeByDateAndMemberId(Long memberId);
}
