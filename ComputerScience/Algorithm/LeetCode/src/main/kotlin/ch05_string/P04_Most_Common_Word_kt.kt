package ch05_string

class P04_Most_Common_Word_kt {
  fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    val counts = mutableMapOf<String, Int>()

    val words = paragraph.replace("\\W+".toRegex(), " ").lowercase().trim().split(" ")

    for (w in words) {
      if (!banned.contains(w)) {
        counts[w] = counts.getOrDefault(w, 0) + 1
      }
    }

    return counts.maxBy { it.value }.key
  }
}