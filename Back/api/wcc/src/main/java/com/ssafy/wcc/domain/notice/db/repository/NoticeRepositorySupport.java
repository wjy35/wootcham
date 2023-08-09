package com.ssafy.wcc.domain.notice.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.wcc.domain.notice.application.dto.request.NoticeRequest;
import com.ssafy.wcc.domain.notice.db.entity.Notice;
import com.ssafy.wcc.domain.notice.db.entity.QNotice;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class NoticeRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager entityManager;

    QNotice notice = QNotice.notice;

    public NoticeRepositorySupport(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(Notice.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    public List<Notice> listNoticeForUsers(long limit) {
        return jpaQueryFactory.select(Projections.bean(Notice.class, notice.subject, notice.content, notice.date))
                .from(notice)
                .orderBy(notice.id.desc())
                .limit(limit)
                .fetch();
    }

    public List<Notice> listNoticeForAdmin() {
        return jpaQueryFactory.select(Projections.bean(Notice.class, notice.id, notice.subject, notice.date))
                .from(notice)
                .fetch();
    }

    public Optional<Notice> getNoticeDetail(long noticeId) {
        return Optional.ofNullable(jpaQueryFactory.select(Projections.bean(Notice.class, notice.subject, notice.content, notice.date))
                .from(notice)
                .where(notice.id.eq(noticeId))
                .fetchOne());
    }

    public Long updateNoticeInfo(NoticeRequest noticeRequest) {
        return jpaQueryFactory.update(notice)
                .set(notice.subject, noticeRequest.getSubject())
                .set(notice.content, noticeRequest.getContent())
                .where(notice.id.eq(noticeRequest.getId()))
                .execute();
    }
}
