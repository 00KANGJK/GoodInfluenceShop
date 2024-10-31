package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.FAQ;
import com.goodinfluenceshop.dto.FAQDto;
import com.goodinfluenceshop.repository.FAQRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FAQService {
    private final FAQRepository faqRepository;

    public void save(FAQDto faqDto) {
        faqRepository.save(faqDto.toEntity());
    }

    public List<FAQ> findAll() {
        return faqRepository.findAllActiveFAQs();
    }

    public FAQ findById(Long id) {
        return faqRepository.findFAQById(id).orElseThrow(() -> new IllegalArgumentException("해당 FAQ가 존재하지 않습니다."));
    }

    public void update(Long id, FAQDto faqDto) {
        FAQ faq = faqRepository.findFAQById(id).orElseThrow(() -> new IllegalArgumentException("해당 FAQ가 존재하지 않습니다."));
        faq.update(faqDto);
    }

    public void delete(Long id) {
        FAQ faq = faqRepository.findFAQById(id).orElseThrow(() -> new IllegalArgumentException("해당 FAQ가 존재하지 않습니다."));
        faq.delete();
    }

    public List<FAQ> getOpenedFAQs() {
        return faqRepository.findOpenedFAQs();
    }

    public FAQ getOpenedFAQ(Long id) {
        return faqRepository.findOpenedFAQById(id).orElseThrow(() -> new IllegalArgumentException("해당 FAQ가 존재하지 않습니다."));
    }
}
