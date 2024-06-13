package com.example.focus.focussession.dto;

import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.domain.Tag;
import com.example.focus.member.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class FocusSessionTagDto {
//    private Long memberId;
    private MemberDto memberDto;
    private FocusSession focusSession;
    private Tag tag;
}
