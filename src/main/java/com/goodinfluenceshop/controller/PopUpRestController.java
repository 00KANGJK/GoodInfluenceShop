package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.FileDto;
import com.goodinfluenceshop.dto.PopUpDto;
import com.goodinfluenceshop.service.FileService;
import com.goodinfluenceshop.service.PopUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PopUpRestController {

  private final PopUpService popUpService;
  private final FileService fileService;

  @PostMapping("/admin/popups")
  public ResponseEntity<PopUpDto> createPopUp(@ModelAttribute PopUpDto.AddPopUpDto addPopUpDto, @RequestParam(value = "file", required = false) List<MultipartFile> files) {
    popUpService.createPopUp(PopUpDto.from(addPopUpDto, fileService.uploadFiles(files, "popup/file")));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/admin/popups")
  public ResponseEntity<List<PopUpDto.ResAdminPopUpDto>> getAllPopUps() {
    return ResponseEntity.ok(PopUpDto.ResAdminPopUpDto.from(popUpService.getAllPopUps()));
  }

  @GetMapping("/all/popups/visible")
  public ResponseEntity<List<PopUpDto.ResAdminPopUpDto>> getVisiblePopUps() {
    return ResponseEntity.ok(PopUpDto.ResAdminPopUpDto.from(popUpService.getVisiblePopUps()));
  }

  @GetMapping("/admin/popups/{id}")
  public ResponseEntity<PopUpDto.ResAdminPopUpDto> getPopUp(@PathVariable Long id) {
    return ResponseEntity.ok(PopUpDto.ResAdminPopUpDto.from(popUpService.getPopUpById(id)));
  }

  @PatchMapping("/admin/popups/{id}")
  public ResponseEntity<Void> updatePopUp(@PathVariable Long id,@ModelAttribute PopUpDto.UpdatePopUpDto updatePopUpDto, @RequestParam(value = "file", required = false) List<MultipartFile> files) {
    popUpService.updatePopUp(id, PopUpDto.from(updatePopUpDto, fileService.uploadFiles(files, "popup/file")));
    return ResponseEntity.ok().build();
  }
  @DeleteMapping("/admin/popups/{id}")
  public ResponseEntity<Void> softDeletePopUp(@PathVariable Long id) {
    popUpService.softDeletePopUp(id);
    return ResponseEntity.ok().build();
  }
}
