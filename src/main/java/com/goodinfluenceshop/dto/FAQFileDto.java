package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.FAQ;
import com.goodinfluenceshop.domain.FAQFile;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FAQFileDto {
    private Long id;
    private String filePath;
    private String originalFileName;

    public static List<FAQFileDto> listFromFileDtos(List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(fileDto -> FAQFileDto.builder()
                        .filePath(fileDto.getFilePath())
                        .originalFileName(fileDto.getOriginalFileName())
                        .build())
                .toList();
    }

    public static List<FAQFileDto> listFromFAQFiles(List<FAQFile> faqFiles) {
        return faqFiles.stream()
                .map(faqFile -> FAQFileDto.builder()
                        .id(faqFile.getId())
                        .filePath(faqFile.getFilePath())
                        .originalFileName(faqFile.getOriginalFileName())
                        .build())
                .toList();
    }

    public static List<FAQFile> listToEntity(List<FAQFileDto> faqFiles, FAQ faq) {
        return faqFiles.stream()
                .map(faqFileDto -> FAQFile.builder()
                        .faq(faq)
                        .filePath(faqFileDto.getFilePath())
                        .originalFileName(faqFileDto.getOriginalFileName())
                        .build())
                .toList();
    }
}
