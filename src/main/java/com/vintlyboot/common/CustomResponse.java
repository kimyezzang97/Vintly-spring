package com.vintlyboot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 모든 파라미터 생성자
@Builder
public class CustomResponse {

    int statusCode;
    String message;
    boolean ok;
}
