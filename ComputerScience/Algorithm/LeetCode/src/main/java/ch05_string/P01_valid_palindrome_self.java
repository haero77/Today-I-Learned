package ch05_string;

import java.util.ArrayList;

public class P01_valid_palindrome_self {
  public boolean isPalindrome(String s) {
    // 영숫자만 남긴 상태에서 앞뒤로 뒤집었을 때 똑같으면 팰린드롬
    var str = s.trim().toLowerCase(); // 공백 제거

    var arr = new ArrayList<String>();

    for (int i = 0; i < str.length(); i++) {
      if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
        || (str.charAt(i) >= '0' && str.charAt(i) <= '9')
      ) {
        arr.add(String.valueOf(str.charAt(i)));
      }
    }

    for (int i = 0; i < arr.size() / 2; i++) {
      // 대칭이 일치하지 않으면 팰린드롬이 아님
      if (!arr.get(i).equals(arr.get(arr.size() - 1 - i))) {
        return false;
      }
    }
    return true;
  }
}