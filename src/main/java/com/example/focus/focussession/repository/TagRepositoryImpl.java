package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.focus.focussession.domain.QFocusSession.focusSession;
import static com.example.focus.focussession.domain.QFocusSessionTag.focusSessionTag;
import static com.example.focus.focussession.domain.QTag.tag;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tag> findTagsByMemberId(Long memberId) {
        return jpaQueryFactory.select(tag)
                .from(tag)
                .join(focusSessionTag.tag, tag)
                .join(focusSessionTag.focusSession, focusSession)
                .where(focusSession.id.eq(memberId))
                .fetch();
    }
}
