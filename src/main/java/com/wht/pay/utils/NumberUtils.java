package com.wht.pay.utils;

public class NumberUtils {

  public static boolean isValidNumber(String num) {
    try {
      Integer.parseInt(num);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
