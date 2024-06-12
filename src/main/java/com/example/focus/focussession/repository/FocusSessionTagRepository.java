package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.FocusSessionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FocusSessionTagRepository extends JpaRepository<FocusSessionTag, Long>{
}
