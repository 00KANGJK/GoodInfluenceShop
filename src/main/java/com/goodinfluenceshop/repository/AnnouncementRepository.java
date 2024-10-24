package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
