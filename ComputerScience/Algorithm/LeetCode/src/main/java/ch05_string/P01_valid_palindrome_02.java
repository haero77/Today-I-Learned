package ch05_string;

public class P01_valid_palindrome_02 {
  public boolean isPalindrome(String s) {
    // 정규식으로 유효한 문자 추출, 모두 소문자 변경
    String s_filtered = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    // 문자열 뒤집기
    String s_reversed = new StringBuilder(s_filtered).reverse().toString();
    // 문자열 비교
    return s_filtered.equals(s_reversed);
  }
}