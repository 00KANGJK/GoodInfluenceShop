package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.domain.enums.AnnouncementCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Announcement extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private boolean isOpened;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnnouncementCategory category;

    @OneToMany(mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<AnnouncementFile> announcementFiles;
}
