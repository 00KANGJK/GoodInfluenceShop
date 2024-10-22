package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Store;
import com.goodinfluenceshop.dto.StoreDto;
import com.goodinfluenceshop.domain.Store;
import com.goodinfluenceshop.dto.StoreDto;
import com.goodinfluenceshop.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StoreService(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    public StoreDto createStore(StoreDto storeDto) {
        Store store = modelMapper.map(storeDto, Store.class);
        store = storeRepository.save(store);
        return modelMapper.map(store, StoreDto.class);
    }

    public List<StoreDto> getAllStore() {
        return storeRepository.findAll().stream()
                .map(Store -> modelMapper.map(Store, StoreDto.class))
                .collect(Collectors.toList());
    }

    public Optional<StoreDto> getStoreById(String no) {
        return storeRepository.findById(no)
                .map(Store -> modelMapper.map(Store, StoreDto.class));
    }

    public StoreDto updateStore(String no, StoreDto storeDto) {
        Store store = storeRepository.findById(no)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // 추가된 항목들 업데이트
        store.setLevel(storeDto.getLevel()); // 회원구분
        store.setStoreTitle(storeDto.getStoreTitle()); // 가게명
        store.setEnrollDate(storeDto.getEnrollDate()); // 신청일자
        store.setDepositCheck(storeDto.getDepositCheck()); // 입금확인
        store.setStickerSend(storeDto.getStickerSend()); // 스티커 발송
        store.setKitSend(storeDto.getKitSend()); // 키트 발송
        store.setSeeAvailable(storeDto.getSeeAvailable()); // 노출 여부
        store.setBusinessType(storeDto.getBusinessType()); // 업종

        store = storeRepository.save(store);
        return modelMapper.map(store, StoreDto.class);
    }


    public void deleteStore(String no) {
        storeRepository.deleteById(no);
    }
}

