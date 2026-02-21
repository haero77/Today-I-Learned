package ch05_string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P03_Reorder_Data_in_Log_Files {
  public String[] reorderLogFiles(String[] logs) {
    // 문자 로그 저장
    List<String> letterList = new ArrayList<>();
    // 숫자 로그
    List<String> digitList = new ArrayList<>();

    for (String log : logs) {
      // 로그 종류 확인 후 숫자 로그라면 숫자 리스트에 삽입
      String digitOrLetter = log.split(" ", 2)[1];
      if (Character.isDigit(digitOrLetter.charAt(0))) {
        digitList.add(log);
      } else {
        letterList.add(log);
      }
    }

    // 문자 리스트 정렬 (1) 문자 순 (2) 식별자 순
    letterList.sort((s1, s2) -> {
      // 컨텐츠
      String con1 = s1.split(" ", 2)[1];
      String con2 = s2.split(" ", 2)[1];

      // 컨텐츠 사전순 비교
      int contentCompare = con1.compareTo(con2);
      if (contentCompare != 0) {
        return contentCompare;
      }

      // 컨텐츠 같으면 식별자 사전순 비교
      String iden1 = s1.split(" ", 2)[0];
      String iden2 = s2.split(" ", 2)[0];
      return iden1.compareTo(iden2);
    });

    // 정렬된 문자 리스트 + 숫자 리스트
    letterList.addAll(digitList);
    return letterList.toArray(new String[0]);
  }
}
