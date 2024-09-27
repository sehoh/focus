package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.Tag;

import java.util.List;

public interface TagRepositorySupport {
    List<Tag> findTagsByMemberId(Long memberId);

    List<Tag> findTagsByEmail(String email);
}
