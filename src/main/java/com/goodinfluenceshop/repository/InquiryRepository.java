package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    @Query("SELECT i FROM Inquiry i WHERE i.deleted = 'N' ORDER BY i.id DESC") // 삭제되지 않은 문의만 조회
    List<Inquiry> findAllActiveInquiries();

    @Query("SELECT i FROM Inquiry i WHERE i.deleted = 'N' AND i.id = :id") // 삭제되지 않은 문의 중 특정 문의 조회
    Optional<Inquiry> findInquiryById(Long id);
}
