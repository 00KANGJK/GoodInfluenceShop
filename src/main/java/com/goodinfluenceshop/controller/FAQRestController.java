package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.FAQDto;
import com.goodinfluenceshop.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FAQRestController {
    private final FAQService faqService;

    @PostMapping("/api/admin/faqs")
    public ResponseEntity<Void> createFAQ(@RequestBody FAQDto.AddFAQDto addFAQDto) {
        faqService.save(FAQDto.from(addFAQDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/admin/faqs")
    public ResponseEntity<List<FAQDto.ResAdminFAQDto>> getFAQs() {
        return ResponseEntity.ok(FAQDto.ResAdminFAQDto.from(faqService.findAll()));
    }

    @GetMapping("/api/admin/faqs/{id}")
    public ResponseEntity<FAQDto.ResAdminFAQDto> getFAQ(@PathVariable Long id) {
        return ResponseEntity.ok(FAQDto.ResAdminFAQDto.from(faqService.findById(id)));
    }

    @PatchMapping("/api/admin/faqs/{id}")
    public ResponseEntity<Void> updateFAQ(@PathVariable Long id, @RequestBody FAQDto.UpdateFAQDto updateFAQDto) {
        faqService.update(id, FAQDto.from(updateFAQDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/admin/faqs/{id}")
    public ResponseEntity<Void> deleteFAQ(@PathVariable Long id) {
        faqService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/all/faqs")
    public ResponseEntity<List<FAQDto.ResFAQDto>> getOpenedFAQs() {
        return ResponseEntity.ok(FAQDto.ResFAQDto.from(faqService.getOpenedFAQs()));
    }

    @GetMapping("/api/all/faqs/{id}")
    public ResponseEntity<FAQDto.ResFAQDto> getOpenedFAQ(@PathVariable Long id) {
        return ResponseEntity.ok(FAQDto.ResFAQDto.from(faqService.getOpenedFAQ(id)));
    }
}
