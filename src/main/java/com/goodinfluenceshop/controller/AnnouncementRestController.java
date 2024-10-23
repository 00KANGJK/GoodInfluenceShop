package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.AnnouncementDto;
import com.goodinfluenceshop.service.AnnouncementService;
import com.goodinfluenceshop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnnouncementRestController {
    private final AnnouncementService announcementService;
    private final FileService fileService;

    @PostMapping("/api/announcements")
    public ResponseEntity<Void> createAnnouncement(@ModelAttribute AnnouncementDto.AddAnnouncementDto addAnnouncementDto, @RequestParam(value = "file", required = false) List<MultipartFile> file) {
        announcementService.createAnnouncement(AnnouncementDto.from(addAnnouncementDto, fileService.uploadFiles(file, "announcement/file")));
        return ResponseEntity.ok().build();
    }
}
