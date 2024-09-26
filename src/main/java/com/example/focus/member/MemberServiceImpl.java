package com.example.focus.member;

import com.example.focus.exception.BadRequestException;
import com.example.focus.focussession.domain.FocusDailyStatistic;
import com.example.focus.focussession.dto.FocusDailyStatisticDto;
import com.example.focus.focussession.dto.MemberListDto;
import com.example.focus.focussession.repository.FocusDailyStatisticRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final FocusDailyStatisticRepository dailyStatisticRepository;

    public MemberServiceImpl(MemberRepository memberRepository, FocusDailyStatisticRepository focusDailyStatisticRepository) {
        this.memberRepository = memberRepository;
        this.dailyStatisticRepository = focusDailyStatisticRepository;
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
    public List<MemberListDto> getMembersDailyStat() {
        List<Member> members = findMembers();
        LocalDate today = LocalDate.now();
        List<MemberListDto> result = new ArrayList<>();

        for (Member member : members) {
            FocusDailyStatisticDto dailyStatisticDto = dailyStatisticRepository
                    .findFocusDailyStatisticByDateAndMember(today, member)
                    .map(FocusDailyStatistic::toDto)
                    .orElseGet(() -> FocusDailyStatisticDto.builder()
                            .member(member)
                            .date(today)
                            .startTime(null)
                            .endTime(null)
                            .totalCumulativeTime(0L)
                            .maxDurationTime(0L)
                            .build()
                    );

            MemberListDto dto = MemberListDto.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .loginType(member.getLoginType())
                    .nickname(member.getNickname())
                    .focusStatus(member.getFocusStatus())
                    .startTime(dailyStatisticDto.getStartTime())
                    .endTime(dailyStatisticDto.getEndTime())
                    .totalCumulativeTime(dailyStatisticDto.getTotalCumulativeTime())
                    .maxDurationTime(dailyStatisticDto.getMaxDurationTime())
                    .build();

            result.add(dto);
        }
        return result;
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
