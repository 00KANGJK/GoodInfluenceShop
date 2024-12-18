package com.goodinfluenceshop.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Donation extends BaseEntity{
  private int totalDonation;
  private int totalCount;
  private int totalSpend;
  private int totalChildrenCount;
}
