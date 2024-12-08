package com.goodinfluenceshop.dto;


import com.goodinfluenceshop.domain.Announcement;
import com.goodinfluenceshop.enums.AnnouncementCategory;
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
    private Boolean isOpened;
    private List<AnnouncementFileDto> announcementFiles;

    public static AnnouncementDto from(AddAnnouncementDto addAnnouncementDto) {
        return AnnouncementDto.builder()
                .title(addAnnouncementDto.getTitle())
                .content(addAnnouncementDto.getContent())
                .category(addAnnouncementDto.getCategory())
                .isOpened(addAnnouncementDto.getIsOpened())
                .announcementFiles(AnnouncementFileDto.listFromFileDtos(addAnnouncementDto.getAnnouncementFiles()))
                .build();
    }

    public static AnnouncementDto from(UpdateAnnouncementDto updateAnnouncementDto) {
        return AnnouncementDto.builder()
                .title(updateAnnouncementDto.getTitle())
                .content(updateAnnouncementDto.getContent())
                .category(updateAnnouncementDto.getCategory())
                .isOpened(updateAnnouncementDto.getIsOpened())
                .announcementFiles(AnnouncementFileDto.listFromFileDtos(updateAnnouncementDto.getAnnouncementFiles()))
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
        private Boolean isOpened;
        private List<FileDto> announcementFiles;
    }

    @Builder
    @Getter
    public static class ResAdminAnnouncementDto {
        private Long id;
        private String title;
        private String content;
        private String category;
        private Boolean isOpened;
        private LocalDateTime createdDate;
        private List<AnnouncementFileDto> announcementFiles;

        public static List<ResAdminAnnouncementDto> from(List<Announcement> announcements) {
            return announcements.stream()
                    .map(announcement -> ResAdminAnnouncementDto.builder()
                            .id(announcement.getId())
                            .title(announcement.getTitle())
                            .content(announcement.getContent())
                            .category(announcement.getCategory().getKor())
                            .isOpened(announcement.getIsOpened())
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
                    .isOpened(announcement.getIsOpened())
                    .createdDate(announcement.getCreatedDate())
                    .announcementFiles(AnnouncementFileDto.listFromAnnouncementFiles(announcement.getAnnouncementFiles()))
                    .build();
        }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class UpdateAnnouncementDto {
        private String title;
        private String content;
        private String category;
        private Boolean isOpened;
        private List<FileDto> announcementFiles;
    }

    @Builder
    @Getter
    public static class ResAnnouncementDto {
        private Long id;
        private String title;
        private String content;
        private String category;
        private Boolean isOpened;
        private LocalDateTime createdDate;
        private List<AnnouncementFileDto> announcementFiles;

        public static List<ResAnnouncementDto> from(List<Announcement> announcements) {
            return announcements.stream()
                    .map(announcement -> ResAnnouncementDto.builder()
                            .id(announcement.getId())
                            .title(announcement.getTitle())
                            .category(announcement.getCategory().getKor())
                            .isOpened(announcement.getIsOpened())
                            .createdDate(announcement.getCreatedDate())
                            .build())
                    .toList();
        }

        public static ResAnnouncementDto from(Announcement announcement) {
            return ResAnnouncementDto.builder()
                    .id(announcement.getId())
                    .title(announcement.getTitle())
                    .content(announcement.getContent())
                    .category(announcement.getCategory().getKor())
                    .isOpened(announcement.getIsOpened())
                    .createdDate(announcement.getCreatedDate())
                    .announcementFiles(AnnouncementFileDto.listFromAnnouncementFiles(announcement.getAnnouncementFiles()))
                    .build();
        }
    }
}
