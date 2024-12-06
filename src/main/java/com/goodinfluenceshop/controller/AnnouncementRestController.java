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

    @PostMapping("/api/admin/announcements")
    public ResponseEntity<Void> createAnnouncement(@RequestBody AnnouncementDto.AddAnnouncementDto addAnnouncementDto) {
        announcementService.createAnnouncement(AnnouncementDto.from(addAnnouncementDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/admin/announcements")
    public ResponseEntity<List<AnnouncementDto.ResAdminAnnouncementDto>> getAnnouncements() {
        return ResponseEntity.ok(AnnouncementDto.ResAdminAnnouncementDto.from(announcementService.getAnnouncements()));
    }

    @GetMapping("/api/admin/announcements/{id}")
    public ResponseEntity<AnnouncementDto.ResAdminAnnouncementDto> getAnnouncement(@PathVariable Long id) {
        return ResponseEntity.ok(AnnouncementDto.ResAdminAnnouncementDto.from(announcementService.getAnnouncement(id)));
    }

    @PatchMapping("/api/admin/announcements/{id}")
    public ResponseEntity<Void> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementDto.UpdateAnnouncementDto updateAnnouncementDto) {
        announcementService.updateAnnouncement(id, AnnouncementDto.from(updateAnnouncementDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/admin/announcements/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/all/announcements")
    public ResponseEntity<List<AnnouncementDto.ResAnnouncementDto>> getOpenedAnnouncements() {
        return ResponseEntity.ok(AnnouncementDto.ResAnnouncementDto.from(announcementService.getOpenedAnnouncements()));
    }

    @GetMapping("/api/all/announcements/{id}")
    public ResponseEntity<AnnouncementDto.ResAnnouncementDto> getOpenedAnnouncement(@PathVariable Long id) {
        return ResponseEntity.ok(AnnouncementDto.ResAnnouncementDto.from(announcementService.getOpenedAnnouncement(id)));
    }
}
