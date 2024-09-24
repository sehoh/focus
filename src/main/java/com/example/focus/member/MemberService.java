package com.example.focus.member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void join(Member member);

    Optional<Member> findMember(Long memberId);

    Member findMemberByEmail(String email);

    List<Member> findMembers();

    void updateFocusStatus(String email, FocusStatus focusStatus);
}
