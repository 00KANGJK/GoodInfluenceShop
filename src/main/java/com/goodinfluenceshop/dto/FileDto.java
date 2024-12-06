package com.goodinfluenceshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private String filePath;
    private String originalFileName;

    @Getter
    @Builder
    public static class ResImageUploadDto {
        private String url;

        public static ResImageUploadDto from(String url) {
            return ResImageUploadDto.builder()
                    .url(url)
                    .build();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class DownloadFileDto {
        private String filePath;
        private String originalFileName;
    }

    @Getter
    @Builder
    public static class ResFileUploadDto {
        private String filePath;
        private String originalFileName;

        public static ResFileUploadDto from(FileDto dto) {
            return ResFileUploadDto.builder()
                    .filePath(dto.getFilePath())
                    .originalFileName(dto.getOriginalFileName())
                    .build();
        }
    }
}
