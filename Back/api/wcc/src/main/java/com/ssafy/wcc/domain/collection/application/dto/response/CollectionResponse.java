package com.ssafy.wcc.domain.collection.application.dto.response;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CollectionResponse {
    private long id;
    private String type;
    private String name;
    private int price;
    private String description;
    private boolean wear;
    private boolean buy;
    private String url;

    @Builder
    public CollectionResponse(long id, int type, String name, int price, String description, boolean wear, boolean buy, String url) {
        this.id = id;
        this.type = getStringType(type);
        this.name = name;
        this.price = price;
        this.description = description;
        this.wear = wear;
        this.buy = buy;
        this.url = url;
    }

    private String getStringType (int type) {
        if (type == 1) return "프로필";
        if (type == 2) return "휘장";
        if (type == 3) return "테두리";

        throw new WCCException(Error.NO_SUCH_ITEM);
    }
}
