package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Donation extends BaseEntity{
  private int totalDonation;
  private int totalCount;
  private int totalSpend;

  public Donation() {
  }

  public Donation(int total_donation, int total_count, int total_spend) {
    this.totalDonation = total_donation;
    this.totalCount = total_count;
    this.totalSpend = total_spend;
  }
}
