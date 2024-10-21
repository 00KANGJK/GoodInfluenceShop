package com.goodinfluenceshop.service;

import com.goodinfluenceshop.repository.DonationRepository;
import com.goodinfluenceshop.dto.DonationDto;
import com.goodinfluenceshop.domain.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonationService {
  private final DonationRepository donationRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public DonationService(DonationRepository donationRepository, ModelMapper modelMapper) {
    this.donationRepository = donationRepository;
    this.modelMapper = modelMapper;
  }

  public DonationDto createDonation(DonationDto donationDto) {
    Donation donation = modelMapper.map(donationDto, Donation.class);
    donation = donationRepository.save(donation);
    return modelMapper.map(donation, DonationDto.class);
  }

  public List<DonationDto> getAllDonations() {
    return donationRepository.findAll().stream()
      .map(donation -> modelMapper.map(donation, DonationDto.class))
      .collect(Collectors.toList());
  }

  public Optional<DonationDto> getDonationById(String id) {
    return donationRepository.findById(id)
      .map(donation -> modelMapper.map(donation, DonationDto.class));
  }

  public DonationDto updateDonation(String id, DonationDto donationDto) {
    Donation donation = donationRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Donation not found"));

    donation.setTotalDonation(donationDto.getTotalDonation());
    donation.setTotalCount(donationDto.getTotalCount());
    donation.setTotalSpend(donationDto.getTotalSpend());

    donation = donationRepository.save(donation);
    return modelMapper.map(donation, DonationDto.class);
  }

  public void deleteDonation(String id) {
    donationRepository.deleteById(id);
  }
}

