package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.InquiryCategory;
import com.goodinfluenceshop.dto.InquiryDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Inquiry extends BaseEntity{
    @Column(nullable = false)
    @Enumerated
    private InquiryCategory category;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private Boolean isSecret;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    private String content;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private Boolean emailChecked;

    @Column(nullable = true)
    private String answer;

    @Column(nullable = true)
    private LocalDateTime answerDate;

    public void reply(InquiryDto.ReplyInquiryDto dto) {
        this.answer = dto.getAnswer();
        this.answerDate = LocalDateTime.now();
    }
}
