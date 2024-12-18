package com.goodinfluenceshop.dto;

import lombok.*;

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

  @Builder
  @Getter
  public static class GetChildrenDto {
    private int totalChildrenCount;
  }
}
