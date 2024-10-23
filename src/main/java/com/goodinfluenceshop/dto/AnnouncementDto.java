package com.goodinfluenceshop.dto;


import com.goodinfluenceshop.domain.Announcement;
import com.goodinfluenceshop.domain.enums.AnnouncementCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class AnnouncementDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private boolean isOpened;
    private List<AnnouncementFileDto> announcementFiles;

    public static AnnouncementDto from(AddAnnouncementDto addAnnouncementDto, List<FileDto> fileDtos) {
        return AnnouncementDto.builder()
                .title(addAnnouncementDto.getTitle())
                .content(addAnnouncementDto.getContent())
                .category(addAnnouncementDto.getCategory())
                .isOpened(addAnnouncementDto.isOpened())
                .announcementFiles(AnnouncementFileDto.listFrom(fileDtos))
                .build();
    }

    public Announcement toEntity() {
        Announcement announcement = Announcement.builder()
                .title(title)
                .content(content)
                .isOpened(isOpened)
                .category(AnnouncementCategory.from(category))
                .build();
        announcement.setAnnouncementFiles(AnnouncementFileDto.listToEntity(announcementFiles, announcement));
        return announcement;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class AddAnnouncementDto {
        private String title;
        private String content;
        private String category;
        private boolean isOpened;
    }
}
