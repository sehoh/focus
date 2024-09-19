package com.example.focus.focussession.service;

import com.example.focus.DateTimeUtils;
import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.dto.CumulativeTimeDto;
import com.example.focus.focussession.dto.CumulativeWeekTimeDto;
import com.example.focus.focussession.dto.FocusSessionDto;
import com.example.focus.focussession.repository.FocusSessionRepository;
import com.example.focus.member.MemberDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FocusSessionServiceImpl implements FocusSessionService{
    private final FocusSessionRepository focusRepository;

    public FocusSessionServiceImpl(FocusSessionRepository repository) {
        this.focusRepository = repository;
    }

    @Override
    public void create(FocusSession focusSession) {
        focusRepository.save(focusSession);
    }

    @Override
    public Optional<FocusSession> findFocusSessionByMemberId(Long memberId) {
        return Optional.empty();
    }

    public List<CumulativeTimeDto> findCumulativeTimeByDateAndMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByDateAndMemberId(memberId);
    }

    public List<CumulativeWeekTimeDto> findCumulativeWeekTimeByWeekAndMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByWeekAndMemberId(memberId);
    }

    @Override
    public List<FocusSession> list() {
        return focusRepository.findAll();
    }
}
