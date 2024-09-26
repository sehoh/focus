package com.example.focus.member;

import com.example.focus.focussession.dto.MemberListDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void join(Member member);

    Optional<Member> findMember(Long memberId);

    Member findMemberByEmail(String email);

    List<Member> findMembers();

    List<MemberListDto> getMembersDailyStat();

    void updateFocusStatus(String email, FocusStatus focusStatus);
}
