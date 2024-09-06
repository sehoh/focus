package com.example.focus.focussession.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CumulativeTimeDto {
    private LocalDate date;
    private Long cumulativeTimeDiff; // 초 단위 누적 시간
}
