package com.goodinfluenceshop.service;

import com.goodinfluenceshop.repository.PopUpRepository;
import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.domain.PopUp;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PopUpService {
  private final PopUpRepository popUpRepository;

  public void createPopUp(PopUpDto popUpDto) {
    popUpRepository.save(popUpDto.toEntity());
  }

  public List<PopUp> getAllPopUps() {
    return popUpRepository.findAll();
  }

  public PopUp getPopUpById(Long id) {
    return popUpRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("해당 PopUp이 존재하지 않습니다."));
  }

  public void updatePopUp(Long id, PopUpDto popUpDto) {
    PopUp popUp = popUpRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("해당 PopUp이 존재하지 않습니다."));
    popUp.update(popUpDto);
  }

  public List<PopUp> getVisiblePopUps() {
    LocalDateTime currentDate = LocalDateTime.now();
    return popUpRepository.findVisiblePopUps(currentDate);
  }
}
