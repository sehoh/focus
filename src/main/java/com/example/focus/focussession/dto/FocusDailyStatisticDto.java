package com.example.focus.focussession.dto;

import com.example.focus.member.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class FocusDailyStatisticDto {
    private Long id;
    private Member member;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long totalCumulativeTime;
    private Long maxDurationTime;
}
