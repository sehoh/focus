package com.example.focus.focussession.dto;

import com.example.focus.member.MemberDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TagRequest {
    private List<String> tags;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String email;

    public FocusSessionDto toFocusSessionDto(MemberDto memberDto) {
        return FocusSessionDto.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .member(memberDto)
                .build();
    }
}
