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
}
