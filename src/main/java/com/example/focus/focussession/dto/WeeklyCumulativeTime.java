package com.example.focus.focussession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WeeklyCumulativeTime {
    private LocalDate weekStart;
    private LocalDate weekEnd;
    private Long cumulativeTimeDiff;
}
