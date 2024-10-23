package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
