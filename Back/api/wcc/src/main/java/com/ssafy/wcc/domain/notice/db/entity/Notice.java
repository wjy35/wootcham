package com.ssafy.wcc.domain.notice.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.wcc.domain.member.db.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "notice")
//@NoArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String subject;

    @Column(nullable = false, length = 500)
    private String content;

    @ColumnDefault("0")
    private Long hit;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @ManyToOne
    private Member member;

    @Builder
    public Notice(String subject, String content, long hit,  Date date, Member member) {
        this.subject = subject;
        this.content = content;
        this.hit = hit;
        this.date = date;
        this.member = member;
    }
}
