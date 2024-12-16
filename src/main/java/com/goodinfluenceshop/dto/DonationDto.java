package com.goodinfluenceshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationDto {
  private Long id;
  private int totalDonation;
  private int totalCount;
  private int totalSpend;
  private int totalChildrenCount;
}
