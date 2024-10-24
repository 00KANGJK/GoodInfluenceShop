package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.exception.FileUploadException;
import com.goodinfluenceshop.service.FileService;
import com.goodinfluenceshop.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.goodinfluenceshop.dto.InquiryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final FileService fileService;

    @PostMapping("/api/inquiries")
    public ResponseEntity<Void> createInquiry(@ModelAttribute InquiryDto.AddInquiryDto dto, @RequestParam(value = "image", required = false) MultipartFile image) throws FileUploadException {
        inquiryService.createInquiry(InquiryDto.from(dto, fileService.upload(image, "inquiry/image/")));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/admin/inquiries")
    public ResponseEntity<List<InquiryDto.ResAdminInquiryDto>> getInquiries() {
        return ResponseEntity.ok(InquiryDto.ResAdminInquiryDto.from(inquiryService.getInquiries()));
    }

    @GetMapping("/api/admin/inquiries/{id}")
    public ResponseEntity<InquiryDto.ResAdminInquiryDto> getInquiry(@PathVariable Long id) {
        return ResponseEntity.ok(InquiryDto.ResAdminInquiryDto.from(inquiryService.getInquiry(id)));
    }

    @PostMapping("/api/admin/inquiries/{id}/reply")
    public ResponseEntity<Void> replyInquiry(@PathVariable Long id, @RequestBody InquiryDto.ReplyInquiryDto dto) {
        inquiryService.replyInquiry(id, dto);
        return ResponseEntity.ok().build();
    }
}
