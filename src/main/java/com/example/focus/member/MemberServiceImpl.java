package com.example.focus.member;

import com.example.focus.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.focus.member.FocusStatus.FOCUS;
import static com.example.focus.member.FocusStatus.NONE;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Member not found with email"));
    }

    public MemberDto findMemberDtoByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(Member::toDto)
                .orElseThrow(() -> new BadRequestException("Member not found with email: "+ email));
    }

    @Override
    @Transactional
    public void updateFocusStatus(String email, FocusStatus focusStatus) {
        Member member = findMemberByEmail(email);
        member.updateStatus(focusStatus);
    }
}
