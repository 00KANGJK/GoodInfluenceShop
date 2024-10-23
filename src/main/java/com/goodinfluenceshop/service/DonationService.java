package com.goodinfluenceshop.service;

import com.goodinfluenceshop.repository.DonationRepository;
import com.goodinfluenceshop.dto.DonationDto;
import com.goodinfluenceshop.domain.Donation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
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

  private DonationDto convertToDto(Donation donation) {
    DonationDto donationDto = new DonationDto();
    donationDto.setId(donation.getId());
    donationDto.setTotalDonation(donation.getTotalDonation());
    donationDto.setTotalCount(donation.getTotalCount());
    donationDto.setTotalSpend(donation.getTotalSpend());
    return donationDto;
  }

  public DonationDto createDonation(DonationDto donationDto) {
    Donation donation = modelMapper.map(donationDto, Donation.class);
    System.out.println("Before saving: " + donation); // 로그 출력
    donation = donationRepository.save(donation);
    System.out.println("After saving: " + donation);
    return modelMapper.map(donation, DonationDto.class);
  }

  public List<DonationDto> getRecentDonations() {
    List<Donation> donations = donationRepository.findRecentDonations();
    return donations.stream()
      .map(this::convertToDto)
      .collect(Collectors.toList());
  }

  public void deleteDonation(Long id) {
    Donation donation = donationRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Donation not found"));
    donation.setDeleted("Y");
    donationRepository.save(donation);
  }


//  public Optional<DonationDto> getDonationById(String id) {
//    return donationRepository.findById(id)
//      .map(donation -> modelMapper.map(donation, DonationDto.class));
//  }
//
//  public DonationDto updateDonation(String id, DonationDto donationDto) {
//    Donation donation = donationRepository.findById(id)
//      .orElseThrow(() -> new RuntimeException("Donation not found"));
//
//    donation.setTotalDonation(donationDto.getTotalDonation());
//    donation.setTotalCount(donationDto.getTotalCount());
//    donation.setTotalSpend(donationDto.getTotalSpend());
//
//    donation = donationRepository.save(donation);
//    return modelMapper.map(donation, DonationDto.class);
//  }
//  public List<DonationDto> getAllDonations() {
//    List<Donation> donations = donationRepository.findAllActiveDonations();
//    return donations.stream()
//      .map(this::convertToDto)
//      .collect(Collectors.toList());
//  }
}

