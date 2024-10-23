package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.Inquiry;
import com.goodinfluenceshop.domain.enums.InquiryCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
public class InquiryDto {
    private final Long id;
    private String category;
    private Integer password;
    private boolean isSecret;
    private String title;
    private String content;
    private String image;
    private String email;
    private boolean emailChecked;
    private String answer;

    public static InquiryDto from(AddInquiryDto dto, FileDto fileDto) {
        return InquiryDto.builder()
                .category(dto.getCategory())
                .password(dto.getPassword())
                .isSecret(dto.isSecret())
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(fileDto.getFilePath())
                .email(dto.getEmail())
                .emailChecked(dto.isEmailChecked())
                .build();
    }

    @Getter
    @NoArgsConstructor
    @Setter
    public static class AddInquiryDto {
        private String category;
        private Integer password;
        private boolean isSecret;
        private String title;
        private String content;
        private String email;
        private boolean emailChecked;
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
}
