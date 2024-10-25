package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.PopUp;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PopUpDto {
  private Long id;
  private String title;
  private String content;
  private boolean isVisible;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<PopUpFileDto> popUpFiles;

  public static PopUpDto from(AddPopUpDto addPopUpDto, List<FileDto> fileDtos) {
    return PopUpDto.builder()
      .title(addPopUpDto.getTitle())
      .content(addPopUpDto.getContent())
      .isVisible(addPopUpDto.getIsVisible())
      .startDate(addPopUpDto.getStartDate())
      .endDate(addPopUpDto.getEndDate())
      .popUpFiles(PopUpFileDto.listFromFileDtos(fileDtos))
      .build();
  }

  public static PopUpDto from(UpdatePopUpDto updatePopUpDto, List<FileDto> fileDtos) {
    return PopUpDto.builder()
      .title(updatePopUpDto.getTitle())
      .content(updatePopUpDto.getContent())
      .isVisible(updatePopUpDto.getIsVisible())
      .startDate(updatePopUpDto.getStartDate())
      .endDate(updatePopUpDto.getEndDate())
      .popUpFiles(PopUpFileDto.listFromFileDtos(fileDtos))
      .build();
  }

  public PopUp toEntity() {
    PopUp popUp = PopUp.builder()
      .title(title)
      .content(content)
      .isVisible(isVisible)
      .startDate(startDate)
      .endDate(endDate)
      .build();
    popUp.setPopUpFiles(PopUpFileDto.listToEntity(popUpFiles, popUp));
    return popUp;
  }

  @NoArgsConstructor
  @Getter
  @Setter
  public static class AddPopUpDto {
    private String title;
    private String content;
    private Boolean isVisible;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
  }

  @NoArgsConstructor
  @Getter
  @Setter
  public static class UpdatePopUpDto {
    private String title;
    private String content;
    private Boolean isVisible;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
  }

  @Getter
  @Builder
  public static class ResAdminPopUpDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isVisible;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<PopUpFileDto> popUpFiles;

    public static ResAdminPopUpDto from(PopUp popUp) {
      return ResAdminPopUpDto.builder()
        .id(popUp.getId())
        .title(popUp.getTitle())
        .content(popUp.getContent())
        .isVisible(popUp.isVisible())
        .startDate(popUp.getStartDate())
        .endDate(popUp.getEndDate())
        .popUpFiles(PopUpFileDto.listFromPopUpFiles(popUp.getPopUpFiles()))
        .build();
    }

    public static List<ResAdminPopUpDto> from(List<PopUp> popUps) {
      return popUps.stream()
        .map(popUp -> ResAdminPopUpDto.builder()
          .id(popUp.getId())
          .title(popUp.getTitle())
          .content(popUp.getContent())
          .isVisible(popUp.isVisible())
          .startDate(popUp.getStartDate())
          .endDate(popUp.getEndDate())
          .popUpFiles(PopUpFileDto.listFromPopUpFiles(popUp.getPopUpFiles()))
          .build())
        .toList();
    }
  }
}
