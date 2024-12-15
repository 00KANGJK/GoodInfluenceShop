package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PopUpFile extends BaseEntity {
    private String filePath;
    private String originalFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "popup_id")
    private PopUp popUp;
}
