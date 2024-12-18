package com.goodinfluenceshop.service;

import com.goodinfluenceshop.domain.Store;
import com.goodinfluenceshop.dto.StoreDto;
import com.goodinfluenceshop.enums.Category;
import com.goodinfluenceshop.enums.MembershipLevel;
import com.goodinfluenceshop.repository.StoreRepository;
import com.goodinfluenceshop.util.ExternalProperties;
import com.goodinfluenceshop.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
  private final StoreRepository storeRepository;
  private final ModelMapper modelMapper;

  private final BusinessVerificationService businessVerificationService;
  private final AuthService authService;
  private final ExternalProperties externalProperties;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public StoreDto createStore(StoreDto storeDto) {
    // 사업자 번호와 이름 검증
    boolean isValid = businessVerificationService.verifyBusiness(storeDto.getBusinessNumber());

    if (!isValid) {
        throw new IllegalArgumentException("유효하지 않은 사업자 등록 정보입니다.");
    }
    System.out.println("check 사업자");

    if (storeRepository.findByStoreEmail(storeDto.getStoreEmail()) != null) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
    }

    storeDto.setPassword(bCryptPasswordEncoder.encode(storeDto.getPassword()));

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

  public String findEmail(String ceoName, String phoneNumber){
      Store store = storeRepository.findAllByPhoneNumberAndCeoName(phoneNumber, ceoName);
      if (store != null && store.getCeoName().equals(ceoName)) {
          return store.getStoreEmail(); // 이메일 반환
      } else {
          throw new IllegalArgumentException("입력하신 정보를 다시 한번 확인해 주세요.");
      }
  }

  public String findpassword(String ceoName, String phoneNumber, String storeEmail){
      Store store = storeRepository.findAllByPhoneNumberAndCeoNameAndStoreEmail(phoneNumber, ceoName, storeEmail);
      if (store != null && store.getCeoName().equals(ceoName)) {
          return store.getPassword(); // 이메일 반환
      } else {
          throw new IllegalArgumentException("입력하신 정보를 다시 한번 확인해 주세요.");
      }
  }


  // 날짜 범위에 맞는 Store 리스트 반환
  public List<StoreDto> getStoresByEnrollDate(LocalDate startDate, LocalDate endDate) {
      return storeRepository.findAllByEnrollDateBetween(startDate, endDate).stream()
              .map(store -> modelMapper.map(store, StoreDto.class)) // Entity -> DTO 변환
              .collect(Collectors.toList());
  }

  // 날짜 기준 + 단일 조건 필터링
  public List<StoreDto> getStoresWithSingleFilter(LocalDate startDate, LocalDate endDate,
                                                  MembershipLevel level, Boolean depositCheck,
                                                  Boolean stickerSend, Boolean kitSend,
                                                  Category businessTypeBig) {
      // 날짜 범위 내 모든 데이터를 가져옵니다.
      List<Store> stores = storeRepository.findAllByEnrollDateBetween(startDate, endDate);

      // 단일 조건에 따라 필터링
      if (level != null) {
          stores = stores.stream()
                  .filter(store -> store.getLevel().equals(level))
                  .collect(Collectors.toList());
      } else if (depositCheck != null) {
          stores = stores.stream()
                  .filter(store -> store.getDepositCheck().equals(depositCheck))
                  .collect(Collectors.toList());
      } else if (stickerSend != null) {
          stores = stores.stream()
                  .filter(store -> store.getStickerSend().equals(stickerSend))
                  .collect(Collectors.toList());
      } else if (kitSend != null) {
          stores = stores.stream()
                  .filter(store -> store.getKitSend().equals(kitSend))
                  .collect(Collectors.toList());
      } else if (businessTypeBig != null) {
          stores = stores.stream()
                  .filter(store -> store.getBusinessTypeBig().equals(businessTypeBig))
                  .collect(Collectors.toList());
      }

      // Entity -> DTO 변환 후 반환
      return stores.stream()
              .map(store -> modelMapper.map(store, StoreDto.class))
              .collect(Collectors.toList());
  }
  // 로그인 기능
  public StoreDto.LoginResDto login(StoreDto.LoginReqDto param) {
    Store store = storeRepository.findByStoreEmail(param.getStoreEmail());
    if (store == null || !bCryptPasswordEncoder.matches(param.getPassword(), store.getPassword())) {
      return StoreDto.LoginResDto.builder().accessToken("not matched").build();
    }

    // Refresh Token 발급
    TokenGenerator tokenGenerator = new TokenGenerator();
    String refreshToken = tokenGenerator.issueRefreshToken(store.getStoreEmail());

    return StoreDto.LoginResDto.builder().accessToken(refreshToken).build();
  }

  // Access Token 재발급 기능
  public StoreDto.LoginResDto access(String refreshToken) throws Exception {
    refreshToken = refreshToken.replace(externalProperties.getTokenPrefix(), "");
    String accessToken = authService.issueAccessToken(refreshToken);

    return StoreDto.LoginResDto.builder().accessToken(accessToken).build();
  }
}

