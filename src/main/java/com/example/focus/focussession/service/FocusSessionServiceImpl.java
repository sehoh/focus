package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.dto.DailyCumulativeTime;
import com.example.focus.focussession.dto.FocusSessionDto;
import com.example.focus.focussession.dto.MonthlyCumulativeTime;
import com.example.focus.focussession.dto.WeeklyCumulativeTime;
import com.example.focus.focussession.repository.FocusSessionQueryRepository;
import com.example.focus.focussession.repository.FocusSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FocusSessionServiceImpl implements FocusSessionService{
    private final FocusSessionRepository focusRepository;
    private final FocusSessionQueryRepository queryRepository;

    public FocusSessionServiceImpl(FocusSessionRepository repository, FocusSessionQueryRepository queryRepository) {
        this.focusRepository = repository;
        this.queryRepository = queryRepository;
    }

    @Override
    public void create(FocusSession focusSession) {
        focusRepository.save(focusSession);
    }

    @Override
    public Optional<FocusSession> findFocusSessionByMemberId(Long memberId) {
        return Optional.empty();
    }

    public List<DailyCumulativeTime> findDailyCumulativeTimeByMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByDateAndMemberId(memberId);
    }

    public List<WeeklyCumulativeTime> findWeeklyCumulativeTimeByMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByWeekAndMemberId(memberId);
    }

    public List<MonthlyCumulativeTime> findMonthlyCumulativeTimeByMemberId(Long memberId) {
        return focusRepository.findCumulativeTimeByMonthAndMemberId(memberId);
    }

    @Override
    public List<FocusSession> list() {
        return focusRepository.findAll();
    }

    @Override
    public List<FocusSessionDto> findFocusSessionByTagName(String tagName) {
        return queryRepository.findSessionsByTagName(tagName).stream().map(FocusSession::toDto).toList();
    }

    @Override
    public List<FocusSessionDto> findFocusSessionByTagNames(List<String> tagNames) {
        return queryRepository.findSessionsByTagNames(tagNames).stream().map(FocusSession::toDto).toList();
    }
}
