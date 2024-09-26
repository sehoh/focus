package com.example.focus.focussession.domain;

import com.example.focus.focussession.dto.FocusDailyStatisticDto;
import com.example.focus.focussession.dto.MemberListDto;
import com.example.focus.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FocusDailyStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long totalCumulativeTime;
    private Long maxDurationTime;

    public FocusDailyStatisticDto toDto() {
        return FocusDailyStatisticDto.builder()
                .id(this.id)
                .member(this.member)
                .date(this.date)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .totalCumulativeTime(this.totalCumulativeTime)
                .maxDurationTime(this.maxDurationTime)
                .build();
    }

    public MemberListDto toMemberListDto() {
        return MemberListDto.builder()
                .id(this.member.getId())
                .username(this.member.getUsername())
                .loginType(this.member.getLoginType())
                .nickname(this.member.getNickname())
                .focusStatus(this.member.getFocusStatus())
                .startTime(this.startTime)
                .endTime(this.endTime)
                .totalCumulativeTime(this.totalCumulativeTime)
                .maxDurationTime(this.maxDurationTime)
                .build();
    }
}
