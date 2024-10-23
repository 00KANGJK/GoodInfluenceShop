package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.exception.FileUploadException;
import com.goodinfluenceshop.service.FileService;
import com.goodinfluenceshop.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.goodinfluenceshop.dto.InquiryDto;
import org.springframework.web.multipart.MultipartFile;

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

}
