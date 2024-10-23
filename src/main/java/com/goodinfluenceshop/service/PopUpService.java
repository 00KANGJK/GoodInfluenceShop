package com.goodinfluenceshop.service;

import com.goodinfluenceshop.repository.PopUpRepository;
import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.domain.PopUp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PopUpService {
  private final PopUpRepository popUpRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public PopUpService(PopUpRepository popUpRepository, ModelMapper modelMapper) {
    this.popUpRepository = popUpRepository;
    this.modelMapper = modelMapper;
  }

  private PopUpDto convertToDto(PopUp popUp) {
    return modelMapper.map(popUp, PopUpDto.class);
  }

  public PopUpDto createPopUp(PopUpDto popUpDto) {
    PopUp popUp = modelMapper.map(popUpDto, PopUp.class);
    popUp = popUpRepository.save(popUp);
    return convertToDto(popUp);
  }

  public List<PopUpDto> getAllPopUps() {
    List<PopUp> popUps = popUpRepository.findAll();
    return popUps.stream()
      .map(this::convertToDto)
      .collect(Collectors.toList());
  }

  public PopUpDto getPopUpById(Long id) {
    PopUp popUp = popUpRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("PopUp not found"));
    return convertToDto(popUp);
  }

  public PopUpDto updatePopUp(Long id, PopUpDto popUpDto) {
    PopUp popUp = popUpRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("PopUp not found"));

    // 필드 업데이트
    popUp.setTitle(popUpDto.getTitle());
    popUp.setContent(popUpDto.getContent());
    popUp.setImageUrl(popUpDto.getImageUrl());
    popUp.setVisible(popUpDto.isVisible());
    popUp.setStartDate(popUpDto.getStartDate());
    popUp.setEndDate(popUpDto.getEndDate());

    popUp = popUpRepository.save(popUp);
    return convertToDto(popUp);
  }

  public void deletePopUp(Long id) {
    PopUp popUp = popUpRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("PopUp not found"));
    popUpRepository.delete(popUp);
  }
}
