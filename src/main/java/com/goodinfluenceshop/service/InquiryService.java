package com.goodinfluenceshop.service;

import com.goodinfluenceshop.dto.InquiryDto;
import com.goodinfluenceshop.repository.InquiryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public void createInquiry(InquiryDto dto) {
        inquiryRepository.save(dto.toEntity());
    }
}
