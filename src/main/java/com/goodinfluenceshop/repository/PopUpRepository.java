package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.PopUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PopUpRepository extends JpaRepository<PopUp, Long> {
  @Query("SELECT p FROM PopUp p WHERE " +
    "(p.isVisible = true OR (p.startDate IS NOT NULL AND p.startDate <= :currentDate)) " +
    "AND (p.endDate IS NULL OR p.endDate >= :currentDate)")
  List<PopUp> findVisiblePopUps(@Param("currentDate") LocalDateTime currentDate);
}
