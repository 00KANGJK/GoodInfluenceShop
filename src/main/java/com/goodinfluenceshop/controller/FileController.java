package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.FileDto;
import com.goodinfluenceshop.exception.FileUploadException;
import com.goodinfluenceshop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/api/all/image")
    public ResponseEntity<FileDto.ResImageUploadDto> uploadImage(MultipartFile image) throws FileUploadException {
        return ResponseEntity.ok(FileDto.ResImageUploadDto.from(fileService.getFileUrl(fileService.upload(image, "image/").getFilePath())));
    }

    @PostMapping("/api/all/file")
    public ResponseEntity<FileDto.ResFileUploadDto> uploadFile(MultipartFile file) throws FileUploadException {
        return ResponseEntity.ok(FileDto.ResFileUploadDto.from(fileService.upload(file, "file/")));
    }

    @PostMapping("/api/all/file/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestBody FileDto.DownloadFileDto dto) {
        String encodedFileName = URLEncoder.encode(dto.getOriginalFileName(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileService.download(dto.getFilePath()));
    }
}
