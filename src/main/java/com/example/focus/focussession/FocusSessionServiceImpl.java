package com.example.focus.focussession;

import com.example.focus.focussession.domain.FocusSession;
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

    @Override
    public List<FocusSession> list() {
        return focusRepository.findAll();
    }
}
