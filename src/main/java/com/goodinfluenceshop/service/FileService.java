package com.goodinfluenceshop.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.goodinfluenceshop.dto.FileDto;
import com.goodinfluenceshop.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final AmazonS3 amazonS3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    public FileDto upload(MultipartFile multipartFile, String baseDirPath) throws FileUploadException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return new FileDto();
        }
        validFileSize(multipartFile);
        String fileName = getUuidFileName(multipartFile.getOriginalFilename());
        String filePath = baseDirPath + "/" + getYearMonthDirPath() + "/" + fileName;
        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, filePath, multipartFile.getInputStream(), null)
                            .withCannedAcl(CannedAccessControlList.PublicRead) // PublicRead 권한으로 업로드 됨
            );
        } catch (IOException e) {
            throw new FileUploadException();
        }
        return FileDto.builder()
                .originalFileName(multipartFile.getOriginalFilename())
                .filePath(filePath)
                .build();
    }

    private void validFileSize(MultipartFile multipartFile) {
        if (multipartFile.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기는 10MB를 넘을 수 없습니다.");
        }
    }

    public String getFileUrl(String filePath) {
        if (filePath == null) {
            return null;
        }
        return amazonS3Client.getUrl(bucket, filePath).toString();
    }

    private static String getUuidFileName(String originalFileName) {
        return UUID.randomUUID() + "_" + originalFileName;
    }

    private static String getYearMonthDirPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        String year = today.format(yearFormatter);
        String month = today.format(monthFormatter);
        return year + "/" + month;
    }

    public List<FileDto> uploadFiles(List<MultipartFile> file, String baseDirPath) {
        if (file == null || file.isEmpty()) {
            return List.of();
        }
        return file.stream()
                .map(f -> {
                    try {
                        return upload(f, baseDirPath);
                    } catch (FileUploadException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public InputStreamResource download(String filePath) {
        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, filePath));
        return new InputStreamResource(s3Object.getObjectContent());
    }
}
