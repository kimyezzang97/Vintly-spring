package com.vintlyboot.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException {
    String statusCode;
    String message;
}
