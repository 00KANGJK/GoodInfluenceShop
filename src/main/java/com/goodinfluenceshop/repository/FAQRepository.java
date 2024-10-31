package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    @Query("SELECT f FROM FAQ f WHERE f.deleted = 'N' ORDER BY f.id DESC") // 삭제되지 않은 FAQ만 조회
    List<FAQ> findAllActiveFAQs();

    @Query("SELECT f FROM FAQ f WHERE f.deleted = 'N' AND f.id = :id") // 삭제되지 않은 FAQ 중 특정 FAQ 조회
    Optional<FAQ> findFAQById(Long id);

    @Query("SELECT f FROM FAQ f WHERE f.deleted = 'N' AND f.isOpened ORDER BY f.id DESC")// 삭제되지 않은 FAQ 중 공개된 것만 조회
    List<FAQ> findOpenedFAQs();

    @Query("SELECT f FROM FAQ f WHERE f.deleted = 'N' AND f.isOpened AND f.id = :id") // 삭제되지 않은 FAQ 중 공개된 특정 FAQ 조회
    Optional<FAQ> findOpenedFAQById(Long id);
}
