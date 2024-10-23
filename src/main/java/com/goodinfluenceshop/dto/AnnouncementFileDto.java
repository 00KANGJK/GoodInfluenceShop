package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.Announcement;
import com.goodinfluenceshop.domain.AnnouncementFile;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AnnouncementFileDto {
    private Long id;
    private String filePath;
    private String originalFileName;

    public static List<AnnouncementFileDto> listFromFileDtos(List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(fileDto -> AnnouncementFileDto.builder()
                        .filePath(fileDto.getFilePath())
                        .originalFileName(fileDto.getOriginalFileName())
                        .build())
                .toList();
    }

    public static List<AnnouncementFileDto> listFromAnnouncementFiles(List<AnnouncementFile> announcementFiles) {
        return announcementFiles.stream()
                .map(announcementFile -> AnnouncementFileDto.builder()
                        .id(announcementFile.getId())
                        .filePath(announcementFile.getFilePath())
                        .originalFileName(announcementFile.getOriginalFileName())
                        .build())
                .toList();
    }

    public static List<AnnouncementFile> listToEntity(List<AnnouncementFileDto> announcementFiles, Announcement announcement) {
        return announcementFiles.stream()
                .map(announcementFileDto -> AnnouncementFile.builder()
                        .announcement(announcement)
                        .filePath(announcementFileDto.getFilePath())
                        .originalFileName(announcementFileDto.getOriginalFileName())
                        .build())
                .toList();
    }
}
