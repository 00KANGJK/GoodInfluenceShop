package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.FAQ;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class FAQDto {
    private Long id;
    private String title;
    private String content;
    private boolean isOpened;
    private List<FAQFileDto> faqFiles;

    public static FAQDto from(AddFAQDto addFAQDto, List<FileDto> fileDtos) {
        return FAQDto.builder()
                .title(addFAQDto.getTitle())
                .content(addFAQDto.getContent())
                .isOpened(addFAQDto.isOpened())
                .faqFiles(FAQFileDto.listFromFileDtos(fileDtos))
                .build();
    }

    public static FAQDto from(UpdateFAQDto updateFAQDto, List<FileDto> fileDtos) {
        return FAQDto.builder()
                .title(updateFAQDto.getTitle())
                .content(updateFAQDto.getContent())
                .isOpened(updateFAQDto.isOpened())
                .faqFiles(FAQFileDto.listFromFileDtos(fileDtos))
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
        private boolean isOpened;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class UpdateFAQDto {
        private String title;
        private String content;
        private boolean isOpened;
    }

    @Builder
    @Getter
    public static class ResAdminFAQDto {
        private Long id;
        private String title;
        private String content;
        private boolean isOpened;
        private List<FAQFileDto> faqFiles;

        public static ResAdminFAQDto from(FAQ faq) {
            return ResAdminFAQDto.builder()
                    .id(faq.getId())
                    .title(faq.getTitle())
                    .content(faq.getContent())
                    .isOpened(faq.isOpened())
                    .faqFiles(FAQFileDto.listFromFAQFiles(faq.getFaqFiles()))
                    .build();
        }

        public static List<ResAdminFAQDto> from(List<FAQ> faqs) {
            return faqs.stream()
                    .map(faq -> ResAdminFAQDto.builder()
                            .id(faq.getId())
                            .title(faq.getTitle())
                            .content(faq.getContent())
                            .isOpened(faq.isOpened())
                            .build())
                    .toList();
        }
    }
}
