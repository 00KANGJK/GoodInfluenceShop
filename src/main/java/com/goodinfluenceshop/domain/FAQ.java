package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.dto.FAQDto;
import com.goodinfluenceshop.dto.FAQFileDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class FAQ extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Boolean isOpened;

    @OneToMany(mappedBy = "faq",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<FAQFile> faqFiles;

    public void update(FAQDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.isOpened = dto.getIsOpened();
        this.faqFiles.clear();
        this.faqFiles.addAll(FAQFileDto.listToEntity(dto.getFaqFiles(), this));
    }
}
