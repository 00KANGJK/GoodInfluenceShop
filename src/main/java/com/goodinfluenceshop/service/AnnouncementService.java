package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Announcement;
import com.goodinfluenceshop.dto.AnnouncementDto;
import com.goodinfluenceshop.repository.AnnouncementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public void createAnnouncement(AnnouncementDto dto) {
        announcementRepository.save(dto.toEntity());
    }

    public List<Announcement> getAnnouncements() {
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncement(Long id) {
        return announcementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
    }

    public void updateAnnouncement(Long id, AnnouncementDto dto) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
        announcement.update(dto);
    }
}
