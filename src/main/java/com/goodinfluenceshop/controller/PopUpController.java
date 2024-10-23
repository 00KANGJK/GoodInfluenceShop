package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.service.PopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/popups")
public class PopUpController {

  private final PopUpService popUpService;

  @Autowired
  public PopUpController(PopUpService popUpService) {
    this.popUpService = popUpService;
  }

  @PostMapping
  public ResponseEntity<PopUpDto> createPopUp(@RequestBody PopUpDto popUpDto) {
    PopUpDto createdPopUp = popUpService.createPopUp(popUpDto);
    return new ResponseEntity<>(createdPopUp, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PopUpDto>> getAllPopUps() {
    List<PopUpDto> popUps = popUpService.getAllPopUps();
    return new ResponseEntity<>(popUps, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PopUpDto> getPopUpById(@PathVariable Long id) {
    PopUpDto popUpDto = popUpService.getPopUpById(id);
    return new ResponseEntity<>(popUpDto, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PopUpDto> updatePopUp(@PathVariable Long id, @RequestBody PopUpDto popUpDto) {
    PopUpDto updatedPopUp = popUpService.updatePopUp(id, popUpDto);
    return new ResponseEntity<>(updatedPopUp, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePopUp(@PathVariable Long id) {
    popUpService.deletePopUp(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
