package com.example.focus.focussession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MonthlyCumulativeTime {
    private LocalDate monthStart;
    private LocalDate monthEnd;
    private Long cumulativeTimeDiff;
}
