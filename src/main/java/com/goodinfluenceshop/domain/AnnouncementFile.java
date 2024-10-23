package com.goodinfluenceshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnouncementFile extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    @Column(nullable = false)
    private String filePath;
}
