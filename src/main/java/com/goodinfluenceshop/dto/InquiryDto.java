package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.Inquiry;
import com.goodinfluenceshop.enums.InquiryCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class InquiryDto {
    private final Long id;
    private String category;
    private String password;
    private Boolean isSecret;
    private String title;
    private String content;
    private String image;
    private String email;
    private Boolean emailChecked;
    private String answer;

    public static InquiryDto from(AddInquiryDto dto, FileDto fileDto) {
        return InquiryDto.builder()
                .category(dto.getCategory())
                .password(dto.getPassword())
                .isSecret(dto.getIsSecret())
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(fileDto.getFilePath())
                .email(dto.getEmail())
                .emailChecked(dto.getEmailChecked())
                .build();
    }

    @Getter
    @NoArgsConstructor
    @Setter
    public static class AddInquiryDto {
        private String category;
        private String password;
        private Boolean isSecret;
        private String title;
        private String content;
        private String email;
        private Boolean emailChecked;
    }

    public Inquiry toEntity() {
        return Inquiry.builder()
                .category(InquiryCategory.from(category))
                .password(password)
                .isSecret(isSecret)
                .title(title)
                .content(content)
                .image(image)
                .email(email)
                .emailChecked(emailChecked)
                .build();
    }

    @Getter
    @Builder
    @Setter
    public static class ResAdminInquiryDto {
        private Long id;
        private String category;
        private String password;
        private Boolean isSecret;
        private String title;
        private String content;
        private String image;
        private String email;
        private Boolean emailChecked;
        private String answer;

        public static ResAdminInquiryDto from(Inquiry inquiry) {
            return ResAdminInquiryDto.builder()
                    .id(inquiry.getId())
                    .category(inquiry.getCategory().getKor())
                    .password(inquiry.getPassword())
                    .isSecret(inquiry.getIsSecret())
                    .title(inquiry.getTitle())
                    .content(inquiry.getContent())
                    .image(inquiry.getImage())
                    .email(inquiry.getEmail())
                    .emailChecked(inquiry.getEmailChecked())
                    .answer(inquiry.getAnswer())
                    .build();
        }

        public static List<ResAdminInquiryDto> from(List<Inquiry> inquiries) {
            return inquiries.stream()
                    .map(ResAdminInquiryDto::from)
                    .toList();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ReplyInquiryDto {
        private String answer;
    }

    @Builder
    @Getter
    @Setter
    public static class ResInquiryDto {
        private Long id;
        private String category;
        private String title;
        private String content;
        private String image;
        private String email;
        private String answer;
        private Boolean emailChecked;
        private Boolean isSecret;

        public static ResInquiryDto from(Inquiry inquiry) {
            return ResInquiryDto.builder()
                    .id(inquiry.getId())
                    .category(inquiry.getCategory().getKor())
                    .title(inquiry.getTitle())
                    .content(inquiry.getContent())
                    .image(inquiry.getImage())
                    .email(inquiry.getEmail())
                    .emailChecked(inquiry.getEmailChecked())
                    .answer(inquiry.getAnswer())
                    .isSecret(inquiry.getIsSecret())
                    .build();
        }

        public static List<ResInquiryDto> from(List<Inquiry> inquiries) {
            return inquiries.stream()
                    .map(inquiry -> ResInquiryDto.builder()
                            .id(inquiry.getId())
                            .category(inquiry.getCategory().getKor())
                            .title(inquiry.getTitle())
                            .isSecret(inquiry.getIsSecret())
                            .build())
                    .toList();
        }
    }
}
