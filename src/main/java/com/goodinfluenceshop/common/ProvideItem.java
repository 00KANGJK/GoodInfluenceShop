package com.goodinfluenceshop.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProvideItem {
    private String name; // 품목 이름
    private int existingPrice; // 기존 가격
    private int providePrice; // 제공 가격
    private boolean freeProvide; // 무료 제공 여부
}
