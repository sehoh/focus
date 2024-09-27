package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.focus.focussession.domain.QFocusSession.focusSession;
import static com.example.focus.focussession.domain.QFocusSessionTag.focusSessionTag;
import static com.example.focus.focussession.domain.QTag.tag;
import static com.example.focus.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tag> findTagsByMemberId(Long memberId) {

        return jpaQueryFactory.select(tag)
                .from(focusSessionTag)
                .join(focusSession).on(focusSessionTag.focusSession.id.eq(focusSession.id))
                .join(member).on(focusSession.member.id.eq(member.id))
                .join(tag).on(focusSessionTag.tag.id.eq(tag.id))
                .where(member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<Tag> findTagsByEmail(String email) {
        return jpaQueryFactory.select(tag)
                .from(focusSessionTag)
                .join(focusSession).on(focusSessionTag.focusSession.id.eq(focusSession.id))
                .join(member).on(focusSession.member.id.eq(member.id))
                .join(tag).on(focusSessionTag.tag.id.eq(tag.id))
                .where(member.email.eq(email))
                .fetch();
    }
}
