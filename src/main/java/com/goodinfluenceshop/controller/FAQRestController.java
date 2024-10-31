package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.FAQDto;
import com.goodinfluenceshop.service.FAQService;
import com.goodinfluenceshop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FAQRestController {
    private final FAQService faqService;
    private final FileService fileService;

    @PostMapping("/api/admin/faqs")
    public ResponseEntity<Void> createFAQ(@ModelAttribute FAQDto.AddFAQDto addFAQDto, @RequestParam(value = "file", required = false) List<MultipartFile> files) {
        faqService.save(FAQDto.from(addFAQDto, fileService.uploadFiles(files, "faq/file")));
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
    public ResponseEntity<Void> updateFAQ(@PathVariable Long id, @ModelAttribute FAQDto.UpdateFAQDto updateFAQDto, @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        faqService.update(id, FAQDto.from(updateFAQDto, fileService.uploadFiles(files, "faq/file")));
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
