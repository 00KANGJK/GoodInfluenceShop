package com.goodinfluenceshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class StoreBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no; // 번호

    @Column(nullable = false)
    private String level; // 회원구분

    @Column(nullable = false)
    private String storeTitle; // 가게명

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime enrollDate; // 신청일자

    @Column(nullable = false)
    private Boolean depositCheck; // 입금확인

    @Column(nullable = false)
    private Boolean stickerSend; // 스티커 발송

    @Column(nullable = false)
    private Boolean kitSend; // 키트 발송

    @Column(nullable = false)
    private Boolean seeAvailable; // 노출 여부

    @Column(nullable = false)
    private String businessType; // 업종

    @Column(nullable = false)
    protected String deleted; // 삭제 여부

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime created_date; // 생성일시

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modify_date; // 수정일시

    @PrePersist
    public void onPrePersist() {
        this.deleted = "N";
        this.created_date = LocalDateTime.now();
    }
}
