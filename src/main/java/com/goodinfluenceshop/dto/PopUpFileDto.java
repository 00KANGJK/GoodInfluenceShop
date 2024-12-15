package com.goodinfluenceshop.dto;

import com.goodinfluenceshop.domain.PopUp;
import com.goodinfluenceshop.domain.PopUpFile;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PopUpFileDto {
    private Long id;
    private String filePath;
    private String originalFileName;

    public static List<PopUpFileDto> listFromFileDtos(List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(fileDto -> PopUpFileDto.builder()
                        .filePath(fileDto.getFilePath())
                        .originalFileName(fileDto.getOriginalFileName())
                        .build())
                .toList();
    }

    public static List<PopUpFileDto> listFromPopUpFiles(List<PopUpFile> popUpFiles) {
        return popUpFiles.stream()
                .map(popUpFile -> PopUpFileDto.builder()
                        .id(popUpFile.getId())
                        .filePath(popUpFile.getFilePath())
                        .originalFileName(popUpFile.getOriginalFileName())
                        .build())
                .toList();
    }

  public static List<PopUpFile> listToEntity(List<PopUpFileDto> popUpFiles, PopUp popUp) {
    return popUpFiles.stream()
      .map(popUpFileDto -> {
        PopUpFile popUpFile = PopUpFile.builder()
          .filePath(popUpFileDto.getFilePath())
          .originalFileName(popUpFileDto.getOriginalFileName())
          .build();
        popUpFile.setPopUp(popUp); // 연관 관계 설정
        return popUpFile;
      })
      .toList();
  }


  public static PopUpFileDto fromFileDto(FileDto fileDto) {
    return PopUpFileDto.builder()
      .filePath(fileDto.getFilePath())
      .originalFileName(fileDto.getOriginalFileName())
      .build();
  }
}
