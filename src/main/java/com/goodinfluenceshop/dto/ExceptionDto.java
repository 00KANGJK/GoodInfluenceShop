package com.goodinfluenceshop.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionDto {
    private String error;
    private String message;
}
