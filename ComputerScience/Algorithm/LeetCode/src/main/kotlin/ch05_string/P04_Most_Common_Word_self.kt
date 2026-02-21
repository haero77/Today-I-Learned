package ch05_string

class P04_Most_Common_Word_self {
  fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    // !?',;.
    val replaced = paragraph
      .replace("!", " ")
      .replace("?", " ")
      .replace("'", " ")
      .replace(",", " ")
      .replace(";", " ")
      .replace(".", " ")

    val pars = replaced.split(" ").toMutableList()

    for ((i, value) in pars.withIndex()) {
      val chars = value.toCharArray()
      var str = ""

      // 구두점 제거
      for (char in chars) {
        if (char.isLetter()) {
          str += char.lowercase()
        }
      }

      // 기존 문자열 변경
      pars[i] = str
    }

    // 단어별 개수 세기
    val wordCount = mutableMapOf<String, Int>()
    for (word in pars) {
      if (word.isBlank()) {
        continue
      }
      wordCount.merge(word, 1, Int::plus)
    }

    // 밴 된 단어 제거
    for (ban in banned) {
      wordCount.remove(ban)
    }

    // 가장 많은 단어 제출
    var maxCountWord = ""
    var maxCount = 0
    for ((word, count) in wordCount) {
      if (count > maxCount) {
        maxCountWord = word
        maxCount = count
      }
    }

    return maxCountWord
  }
}