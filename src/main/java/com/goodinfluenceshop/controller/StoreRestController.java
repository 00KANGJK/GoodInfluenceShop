package com.goodinfluenceshop.controller;


import com.goodinfluenceshop.dto.StoreDto;

import com.goodinfluenceshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/stores")
public class StoreRestController {
    private final StoreService storeService;

   @Autowired
    public StoreRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto) {
        StoreDto createdStore = storeService.createStore(storeDto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> stores = storeService.getAllStore();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{no}")
    public ResponseEntity<StoreDto> getStoreByno(@PathVariable String no) {
        return storeService.getStoreById(no)
                .map(storeDto -> new ResponseEntity<>(storeDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{no}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable String no, @RequestBody StoreDto storeDto) {
        StoreDto updatedStore = storeService.updateStore(no, storeDto);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteStore(@PathVariable String no) {
        storeService.deleteStore(no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
