package com.example.focus.focussession;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FocusSessionDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public FocusSession toEntity() {
        return FocusSession.builder()
                .id(this.id)
                .startDate(this.startDate)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }
}
