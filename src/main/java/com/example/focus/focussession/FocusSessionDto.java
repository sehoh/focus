package com.example.focus.focussession;

import com.example.focus.member.Member;
import com.example.focus.member.MemberDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FocusSessionDto {
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private MemberDto member;

    public FocusSession toEntity() {
        return FocusSession.builder()
                .id(this.id)
                .startDateTime(this.startDateTime)
                .endDateTime(this.endDateTime)
                .member(this.member.toEntity())
                .build();
    }
}
