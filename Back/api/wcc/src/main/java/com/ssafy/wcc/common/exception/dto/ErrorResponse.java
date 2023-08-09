package com.ssafy.wcc.common.exception.dto;

import com.ssafy.wcc.common.exception.WCCException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;


@Getter
@Builder
public class ErrorResponse {
    private final String isSuccess; // 실패 시 항상 false로 응답

    public static ResponseEntity<ErrorResponse> toResponseEntity(WCCException e) {
        return ResponseEntity
                .status(e.getError().getErrorCode())
                .body(ErrorResponse.builder()
                        .isSuccess("false")
                        .build()
                );
    }
}
