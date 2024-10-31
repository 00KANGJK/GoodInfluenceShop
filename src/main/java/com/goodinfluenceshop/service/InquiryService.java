package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Inquiry;
import com.goodinfluenceshop.dto.InquiryDto;
import com.goodinfluenceshop.repository.InquiryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public void createInquiry(InquiryDto dto) {
        inquiryRepository.save(dto.toEntity());
    }

    public List<Inquiry> getInquiries() {
        return inquiryRepository.findAllActiveInquiries();
    }

    public Inquiry getInquiry(Long id) {
        return inquiryRepository.findInquiryById(id).orElseThrow(()->new IllegalArgumentException("해당 문의가 존재하지 않습니다."));
    }

    public void replyInquiry(Long id, InquiryDto.ReplyInquiryDto dto) {
        Inquiry inquiry = inquiryRepository.findInquiryById(id).orElseThrow(()->new IllegalArgumentException("해당 문의가 존재하지 않습니다."));
        inquiry.reply(dto);
    }

    public void deleteInquiry(Long id) {
        Inquiry inquiry = inquiryRepository.findInquiryById(id).orElseThrow(()->new IllegalArgumentException("해당 문의가 존재하지 않습니다."));
        inquiry.delete();
    }

    public Inquiry getPasswordedInquiry(Long id, String password) {
        Inquiry inquiry = inquiryRepository.findInquiryById(id).orElseThrow(()->new IllegalArgumentException("해당 문의가 존재하지 않습니다."));
        if (!inquiry.getIsSecret()) {
            return inquiry;
        }
        if (!inquiry.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return inquiry;
    }
}
