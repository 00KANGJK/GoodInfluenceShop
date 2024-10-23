package com.goodinfluenceshop.exception;

import java.io.IOException;

public class FileUploadException extends IOException {
    public FileUploadException() {
        super("파일 업로드에 실패했습니다.");
    }
}
