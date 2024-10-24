package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PopUpFile extends BaseEntity {
    private String filePath;
    private String originalFileName;
    @ManyToOne
    @JoinColumn(name = "popup_id")
    private PopUp popUp;
}
