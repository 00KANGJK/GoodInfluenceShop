package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.service.PopUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PopUpController {

  private final PopUpService popUpService;

  @PostMapping("/admin/popups")
  public ResponseEntity<PopUpDto> createPopUp(@RequestBody PopUpDto popUpDto) {
    PopUpDto createdPopUp = popUpService.createPopUp(popUpDto);
    return new ResponseEntity<>(createdPopUp, HttpStatus.CREATED);
  }

  @GetMapping("/admin/popups")
  public ResponseEntity<List<PopUpDto>> getAllPopUps() {
    List<PopUpDto> popUps = popUpService.getAllPopUps();
    return new ResponseEntity<>(popUps, HttpStatus.OK);
  }

  @GetMapping("/all/popups/visible")
  public ResponseEntity<List<PopUpDto>> getVisiblePopUps() {
    List<PopUpDto> visiblePopUps = popUpService.getVisiblePopUps();
    return new ResponseEntity<>(visiblePopUps, HttpStatus.OK);
  }

  @PutMapping("/admin/popups/{id}")
  public ResponseEntity<PopUpDto> updatePopUp(@PathVariable Long id, @RequestBody PopUpDto popUpDto) {
    PopUpDto updatedPopUp = popUpService.updatePopUp(id, popUpDto);
    return new ResponseEntity<>(updatedPopUp, HttpStatus.OK);
  }

  @DeleteMapping("/admin/popups/{id}")
  public ResponseEntity<Void> deletePopUp(@PathVariable Long id) {
    popUpService.deletePopUp(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
