package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Store;
import com.goodinfluenceshop.dto.StoreDto;
import com.goodinfluenceshop.enums.Category;
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
//    private final BusinessVerificationService businessVerificationService;

    @Autowired
    public StoreService(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
//        this.businessVerificationService = businessVerificationService;
    }

    public StoreDto createStore(StoreDto storeDto) {
//        // 사업자 번호와 이름 검증
//        boolean isValid = businessVerificationService.verifyBusiness(storeDto.getBusinessNumber(), storeDto.getCeoName(), storeDto.getEnrollDate());
//
//        if (!isValid) {
//            throw new IllegalArgumentException("유효하지 않은 사업자 등록 정보입니다.");
//        }
//        System.out.println("check 사업자");

        Store store = modelMapper.map(storeDto, Store.class);
        store = storeRepository.save(store);
        return modelMapper.map(store, StoreDto.class);
    }

    public List<StoreDto> getAllStore() {
        return storeRepository.findAll().stream()
                .map(Store -> modelMapper.map(Store, StoreDto.class))
                .collect(Collectors.toList());
    }

    public Optional<StoreDto> getStoreById(int no) {
        return storeRepository.findById(no)
                .map(Store -> modelMapper.map(Store, StoreDto.class));
    }

    public StoreDto updateStore(int no, StoreDto storeDto) {
        // Store 엔티티를 조회
        Store store = storeRepository.findById(no)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // DTO의 필드 값을 엔티티에 매핑 (수동 설정)
        store.setLevel(storeDto.getLevel());
        store.setCeoName(storeDto.getCeoName());
        store.setStoreTitle(storeDto.getStoreTitle());
        store.setStoreEmail(storeDto.getStoreEmail());
        store.setPhoneNumber(storeDto.getPhoneNumber());
        store.setPassword(storeDto.getPassword());
        store.setStorePhoneNumber(storeDto.getStorePhoneNumber());
        store.setStoreAddress(storeDto.getStoreAddress());
        store.setStoreDetailAddress(storeDto.getStoreDetailAddress());
        store.setEnrollDate(storeDto.getEnrollDate());
        store.setDepositCheck(storeDto.getDepositCheck());
        store.setStickerSend(storeDto.getStickerSend());
        store.setKitSend(storeDto.getKitSend());
        store.setSeeAvailable(storeDto.getSeeAvailable());
        store.setBusinessTypeBig(storeDto.getBusinessTypeBig());

        Category category;

        // 대분류에 따른 중분류 설정 (유효성 검사 포함)
        if (storeDto.getBusinessTypeBig() != null && storeDto.getBusinessTypeMiddle() != null) {
            Category businessTypeBig = storeDto.getBusinessTypeBig();
            String businessTypeMiddle = storeDto.getBusinessTypeMiddle();

            try {
                switch (businessTypeBig) {
                    case FOOD:
                        store.setBusinessTypeMiddle(Category.FoodSubCategory.valueOf(businessTypeMiddle).name());
                        break;
                    case EDUCATION:
                        store.setBusinessTypeMiddle(Category.EducationSubCategory.valueOf(businessTypeMiddle).name());
                        break;
                    case SERVICE:
                        store.setBusinessTypeMiddle(Category.ServiceSubCategory.valueOf(businessTypeMiddle).name());
                        break;
                    case OTHER:
                        store.setBusinessTypeMiddle(Category.OtherSubCategory.valueOf(businessTypeMiddle).name());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid businessTypeBig category: " + businessTypeBig);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid middle category for the specified businessTypeBig: " + businessTypeBig + " and businessTypeMiddle: " + businessTypeMiddle, e);
            }
        }


        store.setBusinessNumber(storeDto.getBusinessNumber());
        store.setOpened(storeDto.getOpened());
        store.setOpenTime(storeDto.getOpenTime());
        store.setCloseTime(storeDto.getCloseTime());
        store.setOpenBreakTime(storeDto.getOpenBreakTime());
        store.setCloseBreakTime(storeDto.getCloseBreakTime());
        store.setHoliDays(storeDto.getHoliDays());
        store.setProvideTarget1(storeDto.getProvideTarget1());
        store.setProvideTarget2(storeDto.getProvideTarget2());

        // SNS 타입 및 이름 설정
        store.setSnsType1(storeDto.getSnsType1());
        store.setSnsType1Url(storeDto.getSnsType1Url());
        store.setSnsType2(storeDto.getSnsType2());
        store.setSnsType2Url(storeDto.getSnsType2Url());

        // 이미지 필드 설정
        store.setStoreImgCI(storeDto.getStoreImgCI());
        store.setStoreImgFront(storeDto.getStoreImgFront());
        store.setStoreImgInside(storeDto.getStoreImgInside());
        store.setStoreImgMenupan(storeDto.getStoreImgMenupan());
        store.setStoreImgMenu(storeDto.getStoreImgMenu());

        // 제공 품목 리스트 수동 매핑
        store.setProvideItems(storeDto.getProvideItems());

        // 업데이트 후 저장
        store = storeRepository.save(store);

        // Entity -> DTO 매핑 후 반환 (ModelMapper 대신 수동 설정 또는 직접 매핑 가능)
        return modelMapper.map(store, StoreDto.class);
    }



    public void deleteStore(int no) {
        storeRepository.deleteById(no);
    }
}

