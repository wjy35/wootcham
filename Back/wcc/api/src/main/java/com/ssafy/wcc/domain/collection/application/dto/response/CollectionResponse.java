package com.ssafy.wcc.domain.collection.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CollectionResponse {
    private int id;
    private int type;
    private String name;
    private int price;
    private String description;
    private boolean wear;
    private boolean buy;

    @Builder
    public CollectionResponse(int id, int type, String name, int price, String description, boolean wear, boolean buy) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.description = description;
        this.wear = wear;
        this.buy = buy;
    }
}
