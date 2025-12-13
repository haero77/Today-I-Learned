fun validateScoreIsNotNegative(score: Int) {
  if (score !in 0..100) {
    throw IllegalArgumentException("${score}범위는 0에서 100사이여야 합니다.")
  }
}

fun getPassOrFail(score: Int): String {
  return if (score >= 50) {
    "P"
  } else {
    "F"
  }
}

fun getGrade(score: Int): String {
  return if (score >= 90) {
    "A"
  } else if (score >= 80) {
    "B"
  } else if (score >= 70) {
    "C"
  } else {
    "D"
  }
}

fun getGradeWithSwitch(score: Int): String {
  return when (score) {
    in 90..99 -> "A"
    in 80..89 -> "B"
    in 70..79 -> "C"
    else -> "D"
  }
}

fun getGradeWithSwitchV2(score: Int): String {
  return when (score) {
    in 90..99 -> "A"
    in 80..89 -> "B"
    in 70..79 -> "C"
    else -> "D"
  }
}

fun startsWithA(obj: Any): Boolean {
  return when (obj) {
    is String -> obj.startsWith("A")
    else -> false
  }
}

fun judgeNumber(number: Int): String {
//  return when (number) {
//    1, 0, -1 -> "어디서 많이 본 숫자네요!"
//    else -> "1, 0, -1이 아니네요!"
//  }
  /*
    return when {
      number == 1 || number == 0 || number == -1 -> "어디서 많이 본 숫자네요!"
      else -> "1, 0, -1이 아니네요!"
    }
  */
  return if (number in -1..1) {
    "어디서 많이 본 숫자네요!"
  } else {
    "1, 0, -1이 아니네요!"
  }
}

fun judgeNumberV2(number: Int) {
  when {
    number == 0 -> println("0입니다.")
    number % 2 == 0 -> println("짝수입니다.")
    else -> println("홀수입니다.")
  }
}