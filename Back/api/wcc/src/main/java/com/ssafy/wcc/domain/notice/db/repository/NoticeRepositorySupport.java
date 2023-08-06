package com.ssafy.wcc.domain.notice.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.wcc.domain.notice.db.entity.Notice;
import com.ssafy.wcc.domain.notice.db.entity.QNotice;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Notice> listNoticeForUsers(long lim) {
        return jpaQueryFactory.select(Projections.bean(Notice.class, notice.subject, notice.content, notice.date))
                .from(notice)
                .orderBy(notice.id.desc())
                .limit(lim)
                .fetch();
    }

    public List<Notice> listNoticeForAdmin() {
        return jpaQueryFactory.select(Projections.bean(Notice.class, notice.id, notice.subject, notice.date))
                .from(notice)
                .fetch();
    }
}
