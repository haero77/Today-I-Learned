package ch05_string;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P04_Most_Common_Word {
  public String mostCommonWord(String p, String[] banned) {
    Set<String> ban = new HashSet<>(Arrays.asList(banned));
    Map<String, Integer> counts = new HashMap<>();

    // 전처리 작업 후 단어 목록을 배열로 저장. \W: 단어 문자가 아닌 것, +: 연속적인 값, \W+ 연속적으로 단어 문자가 아닌값
    String[] words = p.replaceAll("\\W+", " ").trim().toLowerCase().split(" ");

    for (String w : words) {
      // 금지된 단어가 아닐 경우 개수 처리
      if (!ban.contains(w)) {
        counts.put(w, counts.getOrDefault(w, 0) + 1);
      }
    }

    // 가장 흔한 단어 추출
    return Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
  }
}
