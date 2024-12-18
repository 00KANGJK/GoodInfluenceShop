package com.goodinfluenceshop.util;

import java.util.Arrays;

public class TokenGenerator {
  String temp_key = "12394895761234982101875902389724";
  String prefix = "Bearer/u0020";
  int intervalRefreshToken = 604800;
  int intervalAccessToken = 43200;

  public String issueRefreshToken(String adminId){
    return generateToken(adminId, intervalRefreshToken);
  }

  public String generateToken(String id, int second){
    String returnVal = "";
    String claim = String.valueOf(id);
    long period = 0;
    AES256Cipher aes = new AES256Cipher();
    try{
      NowDate now = new NowDate();
      String due = now.getDue(second);
      System.out.println("claim : " + claim);
      System.out.println("due : " + due);
      returnVal = aes.AES_Encode(temp_key, claim + "_" + due);
    } catch (Exception e){
      System.out.println("error....");
    }
    return prefix + returnVal;
  }
  public String verifyToken(String token) throws Exception {
    String returnVal = "";
    AES256Cipher aes = new AES256Cipher();
    try{
      if(!token.startsWith(prefix)){
        throw new Exception("token does not start with prefix");
      }
      token = token.substring(prefix.length());
      System.out.println("token : " + token);
      // id_만료일
      returnVal = aes.AES_Decode(temp_key, token);
    } catch (Exception e){
      System.out.println("error....");
    }
    if("".equals(returnVal)){
      throw new Exception("....");
    }

    String[] arrayVal = returnVal.split("_");
    String tbuserId = arrayVal[0];
    String due = arrayVal[1];
    NowDate nowDate = new NowDate();
    String now = nowDate.getNow();
    System.out.println("due : " + due);
    System.out.println("now : " + now);

    String[] arrayNow = {due, now};
    Arrays.sort(arrayNow);
    if(due.equals(arrayNow[1])){
      return tbuserId;
    } else {
      throw new Exception("due....");
    }
  }
}
