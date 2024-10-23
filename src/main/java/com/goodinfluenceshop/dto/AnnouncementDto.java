package com.goodinfluenceshop.dto;


import com.goodinfluenceshop.domain.Announcement;
import com.goodinfluenceshop.domain.enums.AnnouncementCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
                .announcementFiles(AnnouncementFileDto.listFromFileDtos(fileDtos))
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

    @Builder
    @Getter
    public static class ResAdminAnnouncementDto {
        private Long id;
        private String title;
        private String content;
        private String category;
        private boolean isOpened;
        private LocalDateTime createdDate;
        private List<AnnouncementFileDto> announcementFiles;

        public static List<ResAdminAnnouncementDto> from(List<Announcement> announcements) {
            return announcements.stream()
                    .map(announcement -> ResAdminAnnouncementDto.builder()
                            .id(announcement.getId())
                            .title(announcement.getTitle())
                            .content(announcement.getContent())
                            .category(announcement.getCategory().getKor())
                            .isOpened(announcement.isOpened())
                            .createdDate(announcement.getCreatedDate())
                            .build())
                    .toList();
        }

        public static ResAdminAnnouncementDto from(Announcement announcement) {
            return ResAdminAnnouncementDto.builder()
                    .id(announcement.getId())
                    .title(announcement.getTitle())
                    .content(announcement.getContent())
                    .category(announcement.getCategory().getKor())
                    .isOpened(announcement.isOpened())
                    .createdDate(announcement.getCreatedDate())
                    .announcementFiles(AnnouncementFileDto.listFromAnnouncementFiles(announcement.getAnnouncementFiles()))
                    .build();
        }
    }
}
