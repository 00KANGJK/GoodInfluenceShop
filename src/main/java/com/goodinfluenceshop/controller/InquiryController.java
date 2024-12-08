package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.service.FileService;
import com.goodinfluenceshop.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.goodinfluenceshop.dto.InquiryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final FileService fileService;

    @PostMapping("/api/all/inquiries")
    public ResponseEntity<Void> createInquiry(@RequestBody InquiryDto.AddInquiryDto dto) {
        inquiryService.createInquiry(InquiryDto.from(dto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/admin/inquiries")
    public ResponseEntity<List<InquiryDto.ResAdminInquiryDto>> getInquiries() {
        List<InquiryDto.ResAdminInquiryDto> inquiries = InquiryDto.ResAdminInquiryDto.from(inquiryService.getInquiries());
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/api/admin/inquiries/{id}")
    public ResponseEntity<InquiryDto.ResAdminInquiryDto> getInquiry(@PathVariable Long id) {
        InquiryDto.ResAdminInquiryDto inquiry = InquiryDto.ResAdminInquiryDto.from(inquiryService.getInquiry(id));
        inquiry.setImage(fileService.getFileUrl(inquiry.getImage()));
        return ResponseEntity.ok(inquiry);
    }

    @PostMapping("/api/admin/inquiries/{id}/reply")
    public ResponseEntity<Void> replyInquiry(@PathVariable Long id, @RequestBody InquiryDto.ReplyInquiryDto dto) {
        inquiryService.replyInquiry(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/admin/inquiries/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/all/inquiries")
    public ResponseEntity<List<InquiryDto.ResInquiryDto>> getOpenedInquiries() {
        List<InquiryDto.ResInquiryDto> inquiries = InquiryDto.ResInquiryDto.from(inquiryService.getInquiries());
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/api/all/inquiries/{id}")
    public ResponseEntity<InquiryDto.ResInquiryDto> getOpenedInquiry(@PathVariable Long id, @RequestParam(value="password", required = false) String password) {
        InquiryDto.ResInquiryDto inquiry = InquiryDto.ResInquiryDto.from(inquiryService.getPasswordedInquiry(id, password));
        inquiry.setImage(fileService.getFileUrl(inquiry.getImage()));
        return ResponseEntity.ok(inquiry);
    }
}
