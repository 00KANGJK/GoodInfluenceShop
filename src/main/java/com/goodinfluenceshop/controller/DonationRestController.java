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
    List<DonationDto> donations = donationService.getAllDonations();
    return new ResponseEntity<>(donations, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DonationDto> getDonationById(@PathVariable String id) {
    return donationService.getDonationById(id)
      .map(donationDto -> new ResponseEntity<>(donationDto, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DonationDto> updateDonation(@PathVariable String id, @RequestBody DonationDto donationDto) {
    DonationDto updatedDonation = donationService.updateDonation(id, donationDto);
    return new ResponseEntity<>(updatedDonation, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDonation(@PathVariable String id) {
    donationService.deleteDonation(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
