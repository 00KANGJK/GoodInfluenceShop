package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.PopUpFile;
import com.goodinfluenceshop.dto.PopUpFileDto;
import com.goodinfluenceshop.repository.PopUpRepository;
import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.domain.PopUp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PopUpService {
  private final PopUpRepository popUpRepository;

  public void createPopUp(PopUpDto popUpDto) {
    // PopUp 엔티티 생성
    PopUp popUp = PopUp.builder()
      .title(popUpDto.getTitle())
      .content(popUpDto.getContent())
      .isVisible(popUpDto.isVisible())
      .startDate(popUpDto.getStartDate())
      .endDate(popUpDto.getEndDate())
      .build();

    // PopUpFiles 매핑
    if (popUpDto.getPopUpFiles() != null && !popUpDto.getPopUpFiles().isEmpty()) {
      List<PopUpFile> popUpFiles = PopUpFileDto.listToEntity(popUpDto.getPopUpFiles(), popUp);
      popUp.setPopUpFiles(popUpFiles);
    }

    // 저장
    popUpRepository.save(popUp);
  }

  public List<PopUp> getAllPopUps() {
    return popUpRepository.findByDeleted("N");
  }

  public PopUp getPopUpById(Long id) {
    return popUpRepository.findByIdAndDeleted(id, "N")
      .orElseThrow(() -> new IllegalArgumentException("해당 PopUp이 존재하지 않거나 삭제되었습니다."));
  }

  public void updatePopUp(Long id, PopUpDto popUpDto) {
    PopUp popUp = popUpRepository.findByIdAndDeleted(id, "N")
      .orElseThrow(() -> new IllegalArgumentException("해당 PopUp이 존재하지 않거나 삭제되었습니다."));
    popUp.update(popUpDto);
    popUpRepository.save(popUp);
  }

  public List<PopUp> getVisiblePopUps() {
    LocalDateTime currentDate = LocalDateTime.now();
    return popUpRepository.findVisiblePopUpsByDeleted(currentDate, "N");
  }

  public void softDeletePopUp(Long id) {
    PopUp popUp = popUpRepository.findByIdAndDeleted(id, "N")
      .orElseThrow(() -> new IllegalArgumentException("해당 PopUp이 존재하지 않습니다."));
    popUp.delete();
    popUpRepository.save(popUp);
  }
}
