package com.goodinfluenceshop.service;

import com.goodinfluenceshop.repository.DonationRepository;
import com.goodinfluenceshop.dto.DonationDto;
import com.goodinfluenceshop.domain.Donation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
public class DonationService {
  private final DonationRepository donationRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public DonationService(DonationRepository donationRepository, ModelMapper modelMapper) {
    this.donationRepository = donationRepository;
    this.modelMapper = modelMapper;
  }

  private DonationDto convertToDto(Donation donation) {
    return modelMapper.map(donation, DonationDto.class);
  }

  public DonationDto createDonation(DonationDto donationDto) {
    Donation donation = modelMapper.map(donationDto, Donation.class);
    donation = donationRepository.save(donation);
    return modelMapper.map(donation, DonationDto.class);
  }

  public DonationDto getRecentDonation() {
    Donation donation = donationRepository.findRecentDonations()
      .stream()
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("No recent donations found"));
    return convertToDto(donation);
  }

  public DonationDto.GetChildrenDto getTotalChildrenCount() {
    Integer latestTotalChildrenCount = donationRepository.findLatestTotalChildrenCount();

    if (latestTotalChildrenCount == null) {
      latestTotalChildrenCount = 0;
    }

    return DonationDto.GetChildrenDto.builder()
      .totalChildrenCount(latestTotalChildrenCount)
      .build();
  }

//  public void deleteDonation(Long id) {
//    Donation donation = donationRepository.findById(id)
//      .orElseThrow(() -> new EntityNotFoundException("Donation not found"));
//    donation.setDeleted("Y");
//    donationRepository.save(donation);
//  }
}

