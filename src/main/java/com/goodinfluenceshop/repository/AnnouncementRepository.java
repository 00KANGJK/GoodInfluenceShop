package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("SELECT a FROM Announcement a WHERE a.deleted = 'N' ORDER BY a.id DESC") // 삭제되지 않은 공지사항만 조회
    List<Announcement> findAllActiveAnnouncements();

    @Query("SELECT a FROM Announcement a WHERE a.deleted = 'N' AND a.isOpened ORDER BY a.id DESC") // 삭제되지 않은 공지사항 중 공개된 것만 조회
    List<Announcement> findOpenedAnnouncements();

    @Query("SELECT a FROM Announcement a WHERE a.deleted='N' AND a.id = :id") // 삭제되지 않은 공지사항 중 특정 공지사항 조회
    Optional<Announcement> findAnnouncementById(Long id);

    @Query("SELECT a FROM Announcement a WHERE a.deleted='N' AND a.isOpened AND a.id = :id") // 삭제되지 않은 공지사항 중 공개된 특정 공지사항 조회
    Optional<Announcement> findOpenedAnnouncementById(Long id);
}
