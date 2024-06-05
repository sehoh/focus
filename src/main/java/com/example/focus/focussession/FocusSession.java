package com.example.focus.focussession;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FocusSession {
    @Id
    @SequenceGenerator(name = "seq_focussession", sequenceName = "seq_focussession", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_focussession")
    @Column(name = "focussession_id")
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public FocusSessionDto toDto() {
        return FocusSessionDto.builder()
                .id(this.id)
                .startDate(this.startDate)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }
}
