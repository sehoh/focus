package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.Tag;

import java.util.List;

public interface FocusSessionTagService {
    // 태그 수정

    // 태그 삭제

    // 태그 조회
    List<Tag> findTagsByMemberId(Long memberId);

}
