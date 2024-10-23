package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.DonationDto;
import com.goodinfluenceshop.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/donations")
public class DonationRestController {
  private final DonationService donationService;

  @Autowired
  public DonationRestController(DonationService donationService) {
    this.donationService = donationService;
  }

  @PostMapping
  public ResponseEntity<DonationDto> createDonation(@RequestBody DonationDto donationDto) {
    DonationDto createdDonation = donationService.createDonation(donationDto);
    return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<DonationDto>> getAllDonations() {
    List<DonationDto> donations = donationService.getRecentDonations();
    return new ResponseEntity<>(donations, HttpStatus.OK);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDonation(@PathVariable String id) {
    donationService.deleteDonation(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
