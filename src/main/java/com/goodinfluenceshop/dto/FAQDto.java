package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.FAQ;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Setter
public class FAQDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isOpened;
    private List<FAQFileDto> faqFiles;

    public static FAQDto from(AddFAQDto addFAQDto) {
        return FAQDto.builder()
                .title(addFAQDto.getTitle())
                .content(addFAQDto.getContent())
                .isOpened(addFAQDto.getIsOpened())
                .faqFiles(FAQFileDto.listFromFileDtos(addFAQDto.getFaqFiles()))
                .build();
    }

    public static FAQDto from(UpdateFAQDto updateFAQDto) {
        return FAQDto.builder()
                .title(updateFAQDto.getTitle())
                .content(updateFAQDto.getContent())
                .isOpened(updateFAQDto.getIsOpened())
                .faqFiles(FAQFileDto.listFromFileDtos(updateFAQDto.getFaqFiles()))
                .build();
    }

    public FAQ toEntity() {
        FAQ faq = FAQ.builder()
                .title(title)
                .content(content)
                .isOpened(isOpened)
                .build();
        faq.setFaqFiles(FAQFileDto.listToEntity(faqFiles, faq));
        return faq;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class AddFAQDto {
        private String title;
        private String content;
        private Boolean isOpened;
        private List<FileDto> faqFiles;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class UpdateFAQDto {
        private String title;
        private String content;
        private Boolean isOpened;
        private List<FileDto> faqFiles;
    }

    @Builder
    @Getter
    public static class ResAdminFAQDto {
        private Long id;
        private String title;
        private String content;
        private Boolean isOpened;
        private LocalDateTime createdDate;
        private List<FAQFileDto> faqFiles;

        public static ResAdminFAQDto from(FAQ faq) {
            return ResAdminFAQDto.builder()
                    .id(faq.getId())
                    .title(faq.getTitle())
                    .content(faq.getContent())
                    .isOpened(faq.getIsOpened())
                    .createdDate(faq.getCreatedDate())
                    .faqFiles(FAQFileDto.listFromFAQFiles(faq.getFaqFiles()))
                    .build();
        }

        public static List<ResAdminFAQDto> from(List<FAQ> faqs) {
            return faqs.stream()
                    .map(faq -> ResAdminFAQDto.builder()
                            .id(faq.getId())
                            .title(faq.getTitle())
                            .content(faq.getContent())
                            .isOpened(faq.getIsOpened())
                            .createdDate(faq.getCreatedDate())
                            .build())
                    .toList();
        }
    }

    @Builder
    @Getter
    public static class ResFAQDto {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private List<FAQFileDto> faqFiles;

        public static ResFAQDto from(FAQ faq) {
            return ResFAQDto.builder()
                    .id(faq.getId())
                    .title(faq.getTitle())
                    .content(faq.getContent())
                    .createdDate(faq.getCreatedDate())
                    .faqFiles(FAQFileDto.listFromFAQFiles(faq.getFaqFiles()))
                    .build();
        }

        public static List<ResFAQDto> from(List<FAQ> faqs) {
            return faqs.stream()
                    .map(faq -> ResFAQDto.builder()
                            .id(faq.getId())
                            .title(faq.getTitle())
                            .content(faq.getContent())
                            .createdDate(faq.getCreatedDate())
                            .build())
                    .toList();
        }
    }
}
