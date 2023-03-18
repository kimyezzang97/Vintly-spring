package com.vintlyboot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse {

    private int statusCode;
    private String message;
    private boolean ok;
    @Builder
    public CustomResponse(int statusCode, String message,boolean ok ){
        this.statusCode = statusCode;
        this.message = message;
        this.ok = ok;
    }
}
