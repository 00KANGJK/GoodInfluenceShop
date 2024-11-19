package com.goodinfluenceshop.repository;

import com.goodinfluenceshop.domain.Donation;
import com.goodinfluenceshop.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {

}
