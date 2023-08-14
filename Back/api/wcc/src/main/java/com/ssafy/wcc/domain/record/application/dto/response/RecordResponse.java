package com.ssafy.wcc.domain.record.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
public class RecordResponse {
    private int rank;
    private String start;
    private String end;
    private int changePoint;
    private int changeMoney;
    private int smileCount;

    @Builder
    public RecordResponse(int rank, Date start, Date end, int changePoint, int changeMoney, int smileCount) {
        this.rank = rank;
        this.start = changeDateFormat(start);
        this.end = changeDateFormat(end);
        this.changePoint = changePoint;
        this.changeMoney = changeMoney;
        this.smileCount = smileCount;
    }

    private String changeDateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
}
