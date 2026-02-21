package ch05_string;

public class P02_Reverse_String {
  public void reverseString(char[] s) {
    int start = 0;
    int end = s.length - 1;

    while(start < end) {
      // 양 끝의 문자를 교환
      char temp = s[start];
      s[start] = s[end];
      s[end] = temp;

      // 포인터 이동
      start++;
      end--;
    }
  }
}