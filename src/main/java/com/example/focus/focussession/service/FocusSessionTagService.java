package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.Tag;
import com.example.focus.focussession.dto.TagDto;

import java.util.List;

public interface FocusSessionTagService {
    // 태그 수정

    // 태그 삭제

    // 태그 조회
    List<Tag> findTagsByMemberId(Long memberId);

    List<TagDto> findTagsByEmail(String email);

}
