package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.DonationDto;
import com.goodinfluenceshop.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DonationRestController {
  private final DonationService donationService;

  @PostMapping("/admin/donations")
  public ResponseEntity<DonationDto> createDonation(@RequestBody DonationDto donationDto) {
    DonationDto createdDonation = donationService.createDonation(donationDto);
    return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
  }

  @GetMapping("/all/donations")
  public ResponseEntity<DonationDto> getRecentDonations() {
    DonationDto donations = donationService.getRecentDonation();
    return new ResponseEntity<>(donations, HttpStatus.OK);
  }

  @GetMapping("/all/donations/total-children-count")
  public DonationDto.GetChildrenDto getTotalChildrenCount() {
    return donationService.getTotalChildrenCount();
  }
}
