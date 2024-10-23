package com.goodinfluenceshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileDto {
    private String filePath;
    private String originalFileName;
}
