package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.FocusSession;

import java.util.List;
import java.util.Optional;

public interface FocusSessionService {
    void create(FocusSession focusSession);

    Optional<FocusSession> findFocusSessionByMemberId(Long memberId);

    List<FocusSession> list();
}
