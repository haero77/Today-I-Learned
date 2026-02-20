package ch05_string

class P03_Reorder_Data_in_Log_Files_with_AI {
  fun reorderLogFiles(logs: Array<String>): Array<String> {
    // 숫자를 별도 바구니에 다음
    val digits = mutableListOf<String>()
    val letters = mutableListOf<String>()

    // 문자, 숫자 구분 (앞 2단어만 확인)
    for (value in logs) {
      // value split해서 2번째 인덱스 확인
      val splitted = value.split(" ")
      val contents = splitted[1]

      // 첫 번째 컨텐츠가 숫자라면 숫자 컨텐츠
      if (contents[0].isDigit()) {
        digits.add(value)
      } else {
        letters.add(value)
      }
    }

    // 문자 바구니를 정렬
    // 컨텐츠 먼저 정렬, 컨텐츠 같으면 식별자 순.
    val sortedLetters = letters.sortedWith(
      compareBy(
        { it.split(" ", limit = 2)[1] }, // 컨텐츠는 스플릿 후 앞 첫 단어를 버린 부분
        { it.split(" ", limit = 2)[0] } // 식별자
      )
    )

    // 문자 + 숫자 바구니 리턴
    val combined = sortedLetters.plus(digits)
    return combined.toTypedArray()
  }
}