package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.enums.Category;
import com.goodinfluenceshop.enums.MembershipLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class StoreBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no; // 번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipLevel level; // 회원 구분

    @Column(nullable = false)
    private String storeTitle; // 가게명

    @Column(nullable = false)
    private LocalDate enrollDate; // 신청일자

    @Column(nullable = false)
    private Boolean depositCheck; // 입금 확인

    @Column(nullable = false)
    private Boolean stickerSend; // 스티커 발송

    @Column(nullable = false)
    private Boolean kitSend; // 키트 발송

    @Column(nullable = false)
    private Boolean seeAvailable; // 노출 여부

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category businessTypeBig; // 업종 대분류

    @Column(nullable = false)
    private String businessTypeMiddle; // 중분류를 문자열로 저장

    @Column(nullable = false)
    private String deleted = "N"; // 삭제 여부 (기본값 "N")

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate; // 생성일시

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifyDate; // 수정일시

    @PrePersist
    public void onPrePersist() {
        this.createdDate = LocalDateTime.now();
    }

    public void setBusinessTypeMiddle(Category businessTypeBig, Enum<?> businessTypeMiddle) {
        if (businessTypeBig != null && businessTypeMiddle != null) {
            if (businessTypeBig == Category.FOOD && businessTypeMiddle instanceof Category.FoodSubCategory) {
                this.businessTypeMiddle = businessTypeMiddle.name();
            } else if (businessTypeBig == Category.EDUCATION && businessTypeMiddle instanceof Category.EducationSubCategory) {
                this.businessTypeMiddle = businessTypeMiddle.name();
            } else if (businessTypeBig == Category.SERVICE && businessTypeMiddle instanceof Category.ServiceSubCategory) {
                this.businessTypeMiddle = businessTypeMiddle.name();
            } else if (businessTypeBig == Category.OTHER && businessTypeMiddle instanceof Category.OtherSubCategory) {
                this.businessTypeMiddle = businessTypeMiddle.name();
            } else {
                throw new IllegalArgumentException("Invalid middle category for the specified businessTypeBig: " + businessTypeBig);
            }
        } else {
            throw new IllegalArgumentException("Both businessTypeBig and businessTypeMiddle must be provided.");
        }
    }
}
