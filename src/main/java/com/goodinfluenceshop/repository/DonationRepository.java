package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, String> {
  @Query("SELECT d FROM Donation d WHERE d.deleted = 'N'") // 삭제되지 않은 기부만 조회
  List<Donation> findAllActiveDonations();

  @Query("SELECT d FROM Donation d WHERE d.deleted = 'N' ORDER BY d.created_date DESC LIMIT 1") // 최신순으로 기부금 입력 내역 조회
  List<Donation> findRecentDonations();
}
