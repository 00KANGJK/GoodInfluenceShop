package com.goodinfluenceshop.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class BusinessVerificationService {

    private final RestTemplate restTemplate;

    @Value("${api.serviceKey}")
    private String serviceKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BusinessVerificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyBusiness(String businessNumber) {
        try {
            //encode
            serviceKey = "uZsPMDcyK5akFJ4jPK5ItcEU4aHfMBFw1um6pAjSr6FjYHpA7eemFsTQGMiCOpnfkoZ5Uju%2BeLkcWAAZhPttYA%3D%3D";
            //decode
            //serviceKey = "uZsPMDcyK5akFJ4jPK5ItcEU4aHfMBFw1um6pAjSr6FjYHpA7eemFsTQGMiCOpnfkoZ5Uju+eLkcWAAZhPttYA==";
            //String serviceKey1 = URLDecoder.decode(serviceKey, "UTF-8");
            String url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=" + serviceKey;
            System.out.println(url);
            URI uri = new URI(url);

            // JSON 요청 본문 생성
            String jsonRequestBody = "{ \"b_no\": [\"" + businessNumber + "\"] }";

            // Content-Type 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            // HttpEntity에 JSON 본문과 헤더 설정 추가
            HttpEntity<String> request = new HttpEntity<>(jsonRequestBody, headers);

            System.out.println(request);

            // API 호출
            ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

            // 응답 출력
            System.out.println("Response: " + response.getBody());

            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataNode = rootNode.path("data");

            // "data" 필드가 존재하고 리스트가 비어 있지 않은 경우
            if (dataNode.isArray() && dataNode.size() > 0) {
                // 첫 번째 요소에서 "b_stt" 값을 확인
                JsonNode firstBusinessInfo = dataNode.get(0);
                String status = firstBusinessInfo.path("b_stt").asText();
                System.out.println("Status: " + status);

                // "b_stt" 값이 "계속사업자"인지 확인
                return "계속사업자".equals(status);
            }

            return false;
        } catch (Exception e) {
            System.err.println("API 호출 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
