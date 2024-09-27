package com.example.focus.focussession.service;

import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.domain.FocusSessionTag;
import com.example.focus.focussession.domain.Tag;
import com.example.focus.focussession.dto.FocusSessionTagDto;
import com.example.focus.focussession.dto.TagDto;
import com.example.focus.focussession.dto.TagRequest;
import com.example.focus.focussession.repository.FocusSessionRepository;
import com.example.focus.focussession.repository.FocusSessionTagRepository;
import com.example.focus.focussession.repository.TagRepository;
import com.example.focus.member.Member;
import com.example.focus.member.MemberDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public List<FocusSessionTagDto> createByTagRequest(TagRequest tagRequest, MemberDto memberDto) {
        // 1 태그 등록
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagRequest.getTags()) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(Tag.builder()
                            .name(tagName)
                            .created(LocalDate.now()).build()));
            tags.add(tag);
        }

        /*
        각 태그에 대한 entity 정보들을 집중세션태그 등록시에 사용되어져야 함!
         */

        // 2 집중세션 등록
        FocusSession focusSession = focusSessionRepository.save(FocusSession.builder()
                .member(memberDto.toEntity())
                .startDateTime(tagRequest.getStartDateTime())
                .endDateTime(tagRequest.getEndDateTime()).build());

       /*
       * 집중세션에 대한 entity 정보를 집중세션태그 등록시에 사용되어저야 함!
       * */
        List<FocusSessionTagDto> focusSessionTagList = new ArrayList<FocusSessionTagDto>();
        // 3 집중세션태그 등록
        for (Tag tag : tags) {
            focusSessionTagList.add(focusTagRepository.save(FocusSessionTag.builder()
                    .tag(tag)
                    .focusSession(focusSession).build()).toDto());
        }
        return focusSessionTagList;
    }

    @Override
    public List<Tag> findTagsByMemberId(Long memberId) {
        return tagRepository.findTagsByMemberId(memberId);
    }

    @Override
    public List<TagDto> findTagsByEmail(String email) {

        return tagRepository.findTagsByEmail(email).stream().map(Tag::toDto).toList();
    }

    public List<Tag> findTags() {
        return tagRepository.findAll();
    }
}
