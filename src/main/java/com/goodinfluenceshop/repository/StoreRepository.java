package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Donation;
import com.goodinfluenceshop.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findAllByPhoneNumberAndCeoName(String phoneNumber, String ceoName);

    Store findAllByPhoneNumberAndCeoNameAndStoreEmail(String phoneNumber, String ceoName, String storeEmail);

    List<Store> findAllByEnrollDateBetween(LocalDate startDate, LocalDate endDate);

    Store findByStoreEmail(String storeEmail);
}
