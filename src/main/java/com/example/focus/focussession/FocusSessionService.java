package com.example.focus.focussession;

import java.util.List;
import java.util.Optional;

public interface FocusSessionService {
    void save(FocusSession focusSession);

    Optional<FocusSession> findFocusSessionByMemberId(Long memberId);

    List<FocusSession> findFocusSessions();
}
