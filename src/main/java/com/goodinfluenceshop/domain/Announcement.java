package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.AnnouncementCategory;
import com.goodinfluenceshop.dto.AnnouncementDto;
import com.goodinfluenceshop.dto.AnnouncementFileDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Announcement extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Boolean isOpened;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnnouncementCategory category;

    @OneToMany(mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<AnnouncementFile> announcementFiles;

    public void update(AnnouncementDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.isOpened = dto.getIsOpened();
        this.category = AnnouncementCategory.from(dto.getCategory());
        this.announcementFiles.clear();
        this.announcementFiles.addAll(AnnouncementFileDto.listToEntity(dto.getAnnouncementFiles(), this));
    }
}
