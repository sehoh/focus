package com.example.focus.focussession.repository;

import com.example.focus.focussession.domain.FocusSession;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.focus.focussession.domain.QFocusSession.focusSession;
import static com.example.focus.focussession.domain.QFocusSessionTag.focusSessionTag;
import static com.example.focus.focussession.domain.QTag.tag;

@Repository
public class FocusSessionQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FocusSessionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<FocusSession> findSessionsByTagName(String tagName) {

        return queryFactory.selectFrom(focusSession)
                .join(focusSessionTag).on(focusSession.id.eq(focusSessionTag.focusSession.id))
                .join(tag).on(focusSessionTag.tag.id.eq(tag.id))
                .where(tag.name.eq(tagName))
                .fetch();
    }

    public List<FocusSession> findSessionsByTagNames(List<String> tagNames) {
        return queryFactory
                .selectFrom(focusSession)
                .where(focusSession.id.in(
                        JPAExpressions
                                .select(focusSession.id)
                                .from(focusSession)
                                .join(focusSessionTag).on(focusSessionTag.focusSession.id.eq(focusSession.id))
                                .join(tag).on(focusSessionTag.tag.id.eq(tag.id))
                                .where(tag.name.in(tagNames))
                                .groupBy(focusSession.id)
                                .having(tag.id.countDistinct().eq((long) tagNames.size()))
                ))
                .fetch();
    }

}
