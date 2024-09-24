package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.dto.MonthlyCumulativeTime;
import com.example.focus.focussession.dto.DailyCumulativeTime;
import com.example.focus.focussession.dto.WeeklyCumulativeTime;
import com.example.focus.focussession.repository.FocusSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<DailyCumulativeTime> findCumulativeTimeByDateAndMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByDateAndMemberId(memberId);
    }

    public List<WeeklyCumulativeTime> findCumulativeWeekTimeByWeekAndMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByWeekAndMemberId(memberId);
    }

    public List<MonthlyCumulativeTime> findMonthlyCumulativeTimeByMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByMonthAndMemberId(memberId);
    }

    @Override
    public List<FocusSession> list() {
        return focusRepository.findAll();
    }
}
