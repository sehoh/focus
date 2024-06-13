package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.domain.FocusSessionTag;
import com.example.focus.focussession.domain.Tag;
import com.example.focus.focussession.dto.FocusSessionTagDto;
import com.example.focus.focussession.repository.FocusSessionRepository;
import com.example.focus.focussession.repository.FocusSessionTagRepository;
import com.example.focus.focussession.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusSessionTagServiceImpl implements FocusSessionTagService{
    private final FocusSessionTagRepository focusTagRepository;
    private final FocusSessionRepository focusSessionRepository;
    private final TagRepository tagRepository;

    public FocusSessionTagServiceImpl(FocusSessionTagRepository focusTagRepository, TagRepository tagRepository, FocusSessionRepository focusSessionRepository) {
        this.focusTagRepository = focusTagRepository;
        this.tagRepository = tagRepository;
        this.focusSessionRepository = focusSessionRepository;
    }

    // 태그 생성(이미 있으면 그대로, 없으면 저장)
    public void create(FocusSessionTagDto focusSessionTagDto) {
        FocusSession focusSession = focusSessionRepository.save(focusSessionTagDto.getFocusSession());
        // TODO : Tag는 save메서드의 merge가 되면 안됨!
        Tag tag = tagRepository.save(focusSessionTagDto.getTag());
        focusTagRepository.save(
                FocusSessionTag.builder().
                        focusSession(focusSession).
                        tag(tag).build());
    }

    @Override
    public List<Tag> findTagsByMemberId(Long memberId) {
        return tagRepository.findTagsByMemberId(memberId);
    }

    public List<Tag> findTags() {
        return tagRepository.findAll();
    }
}
