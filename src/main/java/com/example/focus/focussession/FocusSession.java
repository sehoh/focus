package com.example.focus.focussession;

import com.example.focus.member.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "focussession_id")
    private Long id;

    private LocalDateTime startDateTime; // 시작 시각
    private LocalDateTime endDateTime; // 종료 시각

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public FocusSessionDto toDto() {
        return FocusSessionDto.builder()
                .id(this.id)
                .startDateTime(this.startDateTime)
                .endDateTime(this.endDateTime)
                .build();
    }
}
