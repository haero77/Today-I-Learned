package ch05_string

class P03_Reorder_Data_in_Log_Files_kt {
  fun reorderLogFiles(logs: Array<String>): Array<String> {
    val letters = mutableListOf<String>()
    val digits = mutableListOf<String>()

    for (log in logs) {
      if (Character.isDigit(log.split(" ", limit = 2)[1][0])) {
        digits.add(log)
      } else {
        letters.add(log)
      }
    }

    // 문자 리스트 정렬
    letters.sortWith(Comparator { s1: String, s2: String ->
      // 식별자와 컨텐츠로 나누기
      val s1x = s1.split(" ", limit = 2)
      val s2x = s2.split(" ", limit = 2)

      // 컨텐츠 사전순 비교
      val compared = s1x[1].compareTo(s2x[1])
      if (compared == 0) {
        // 컨텐츠 사전순 같을 경우 식별자 사전순 비교
        s1x[0].compareTo(s2x[0])
      } else {
        compared
      }
    })

    // 정렬된 문자 리스트 + 숫자 리스트
    letters.addAll(digits)

    // 결과 반환
    return letters.toTypedArray()
  }
}