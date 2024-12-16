package com.goodinfluenceshop.domain;

import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.dto.PopUpFileDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class PopUp extends BaseEntity {

  private String title; // 제목
  private String content; // 내용

  @Column(nullable = false)
  private boolean isVisible; // 팝업 표시 여부

  @Column(name = "start_date")
  private LocalDateTime startDate; // 팝업 공개 시작일

  @Column(name = "end_date")
  private LocalDateTime endDate; // 팝업 공개 종료일

  @OneToMany(mappedBy = "popUp",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
  private List<PopUpFile> popUpFiles; // 팝업 파일 목록

  public void update(PopUpDto dto) {
    this.title = dto.getTitle();
    this.content = dto.getContent();
    this.isVisible = dto.isVisible();
    this.startDate = dto.getStartDate();
    this.endDate = dto.getEndDate();

    if (dto.getPopUpFiles() != null && !dto.getPopUpFiles().isEmpty()) {
      List<PopUpFile> newFiles = PopUpFileDto.listToEntity(dto.getPopUpFiles(), this);
      this.popUpFiles.clear();
      this.popUpFiles.addAll(newFiles);
    }
  }
}
