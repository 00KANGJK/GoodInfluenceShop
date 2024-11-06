//package com.goodinfluenceshop.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class BusinessVerificationService {
//
//    private final RestTemplate restTemplate;
//
//    @Value("${api.serviceKey}")
//    private String serviceKey; // application.yml 에서 설정된 서비스 키
//
//    public BusinessVerificationService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public boolean verifyBusiness(String businessNumber, String businessName, String enrollDate) {
//        String url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=" + serviceKey;
//
//        // 요청 바디 설정
//        Map<String, Object> businessData = new HashMap<>();
//        businessData.put("b_no", businessNumber);
//        businessData.put("start_dt", enrollDate); // 개업 연월일 추가
//        businessData.put("p_nm", businessName);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("businesses", Collections.singletonList(businessData));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
//
//        // API 호출
//        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
//
//        // 응답 처리
//        List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");
//        if (data != null && !data.isEmpty()) {
//            Map<String, Object> businessInfo = data.get(0);
//            String status = (String) businessInfo.get("b_stt");
//            return "영업".equals(status); // "영업" 상태이면 유효한 사업자로 판단
//        }
//        return false;
//    }
//}
