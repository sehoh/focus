package com.example.focus.focussession.dto;

import com.example.focus.member.FocusStatus;
import com.example.focus.member.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class MemberListDto {
    private Long id;
    private String username;
    private LoginType loginType;
    private String nickname;
    private FocusStatus focusStatus;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long totalCumulativeTime;
    private Long maxDurationTime;
}
