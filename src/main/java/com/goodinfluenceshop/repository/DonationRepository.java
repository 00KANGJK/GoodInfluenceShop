package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, String> {
}
