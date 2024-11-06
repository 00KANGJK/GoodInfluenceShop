package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.MembershipLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class StoreBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no; // 번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MembershipLevel level; // 회원 구분

    @Column(nullable = false)
    String storeTitle; // 가게명

    @Column(nullable = false)
    String enrollDate; // 신청일자

    @Column(nullable = false)
     Boolean depositCheck; // 입금 확인

    @Column(nullable = false)
     Boolean stickerSend; // 스티커 발송

    @Column(nullable = false)
     Boolean kitSend; // 키트 발송

    @Column(nullable = false)
     Boolean seeAvailable; // 노출 여부

    @Column(nullable = false)
     String businessType; // 업종

    @Column(nullable = false)
    protected String deleted; // 삭제 여부

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdDate; // 생성일시

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifyDate; // 수정일시

    @PrePersist
    public void onPrePersist() {
        this.deleted = "N";
        this.createdDate = LocalDateTime.now();
    }
}
