package com.ssafy.wcc.domain.collection.db.entity;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    private List<MemberItem> memberItems = new ArrayList<>();

    @Builder
    public CollectionItem(Long id, int type, String name, int price, String description, List<MemberItem> memberItems) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.description = description;
        this.memberItems = memberItems;
    }
}
