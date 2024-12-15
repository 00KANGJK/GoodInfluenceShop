package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.PopUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PopUpRepository extends JpaRepository<PopUp, Long> {
  @Query("SELECT p FROM PopUp p WHERE p.deleted = :deleted AND p.isVisible = true AND p.startDate <= :currentDate AND p.endDate >= :currentDate")
  List<PopUp> findVisiblePopUpsByDeleted(@Param("currentDate") LocalDateTime currentDate, @Param("deleted") String deleted);

  // 삭제되지 않은 모든 PopUp 조회
  List<PopUp> findByDeleted(String deleted);

  // 특정 ID의 삭제되지 않은 PopUp 조회
  Optional<PopUp> findByIdAndDeleted(Long id, String deleted);
}
