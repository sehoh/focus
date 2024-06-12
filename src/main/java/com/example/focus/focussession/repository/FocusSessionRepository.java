package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.FocusSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FocusSessionRepository extends JpaRepository<FocusSession, Long> {
}
