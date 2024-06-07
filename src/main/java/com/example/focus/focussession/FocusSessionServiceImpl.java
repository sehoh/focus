package com.example.focus.focussession;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(FocusSession focusSession) {
        focusRepository.save(focusSession);
    }

    // TODO :: JPQL SQL 작성
    @Override
    public Optional<FocusSession> findFocusSessionByMemberId(Long memberId) {
        return Optional.empty();
    }

    @Override
    public List<FocusSession> findFocusSessions() {
        return focusRepository.findAll();
    }
}
