package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.Inquiry;
import com.goodinfluenceshop.enums.InquiryCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public static InquiryDto from(AddInquiryDto dto) {
        return InquiryDto.builder()
                .category(dto.getCategory())
                .password(dto.getPassword())
                .isSecret(dto.getIsSecret())
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(dto.getImage())
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
        private String image;
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
        private LocalDateTime createdDate;

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
                    .createdDate(inquiry.getCreatedDate())
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
        private LocalDateTime createdDate;
        private LocalDateTime answerDate;

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
                    .createdDate(inquiry.getCreatedDate())
                    .isSecret(inquiry.getIsSecret())
                    .answerDate(inquiry.getAnswerDate())
                    .build();
        }

        public static List<ResInquiryDto> from(List<Inquiry> inquiries) {
            return inquiries.stream()
                    .map(inquiry -> ResInquiryDto.builder()
                            .id(inquiry.getId())
                            .category(inquiry.getCategory().getKor())
                            .title(inquiry.getTitle())
                            .isSecret(inquiry.getIsSecret())
                            .content(inquiry.getContent())
                            .createdDate(inquiry.getCreatedDate())
                            .build())
                    .toList();
        }
    }
}
