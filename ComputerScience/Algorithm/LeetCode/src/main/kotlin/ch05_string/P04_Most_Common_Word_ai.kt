package ch05_string

class P04_Most_Common_Word_ai {
  fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    // 0. 금지 단어 목록을 Set으로 변환하여 검색 속도를 O(1)로 최적화
    val bannedSet = banned.toSet()

    return paragraph
      // 1. 소문자로 변환: "Bob", "bob"을 동일하게 취급하기 위함
      .lowercase()

      // 2. 단어 추출: 알파벳이 아닌 모든 문자(공백, 쉼표, 마침표 등)를 기준으로 쪼갬
      // 결과 예시: ["bob", "hit", "a", "ball", "the", "hit", "ball", "far", "after", "it", "was", "hit"]
      .split(Regex("[^a-z]+"))

      // 3. 필터링: split 결과로 생긴 빈 문자열("")과 금지 목록에 포함된 단어를 제거
      .filter { it.isNotEmpty() && it !in bannedSet }

      // 4. 그룹화: 동일한 단어끼리 묶어주는 준비 단계
      // 내부적으로는 { "ball" -> ["ball", "ball"], "hit" -> ["hit", "hit", "hit"] ... } 구조를 지향함
      .groupingBy { it }

      // 5. 빈도수 계산: 각 그룹에 속한 요소의 개수를 세어 Map<String, Int>로 반환
      // 결과 예시: { "ball" to 2, "hit" to 3, ... }
      .eachCount()

      // 6. 최댓값 탐색: Map에서 value(빈도수)가 가장 큰 엔트리를 찾음
      // 결과 예시: Entry("hit", 3)
      .maxBy { it.value }

      // 7. 결과 반환: 가장 빈번한 단어의 key를 반환
      .key
  }
}