package com.ssafy.wcc.domain.member.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.record.db.entity.RecordKey;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "member_item")
public class MemberItem {

    private boolean wear;

    private boolean buy;

    @EmbeddedId
    private MemberItemPK memberItemPK;

    @ManyToOne
    @MapsId("memberId")
    private Member member;

    @ManyToOne
    @MapsId("collectionId")
    private CollectionItem collection;
}
