import org.junit.jupiter.api.Test

class 문자열 {
  @Test
  fun 문자열_기본() {
    val str = "Hello, World!"

    // 문자열 인덱스
    println(str[0]) // H
    println(str[7]) // W

    // typpe of str[7] is Char
    println("type: ${str[7]::class}") // class kotlin.Char

    // 문자열 길이
    println(str.length) // 13

    // 문자열 마지막 인덱스
    println(str[str.length - 1]) // !
    println(str[str.lastIndex])
    println(str.last())          // ! (더 간결한 방법)
  }

  @Test
  fun 문자열_탐색() {
    val str = "Hello, World!"

    // 포함 여부
    println(str.contains("World")) // true

    // 시작/끝 문자 확인
    println(str.startsWith("Hello")) // true
    println(str.endsWith("!"))       // true

    // 인덱스 찾기 (없으면 -1)
    println(str.indexOf("o"))        // 4 (첫 번째 o)
    println(str.lastIndexOf("o"))    // 8 (마지막 o)

    // 문자 개수 세기
    println(str.count { it == 'l' }) // 3
  }

  @Test
  fun 문자열_변환() {
    val str = "Hello, World!"

    // 대소문자
    println(str.uppercase()) // HELLO, WORLD!
    println(str.lowercase()) // hello, world!

    // 공백 제거
    val padded = "  hello  "
    println(padded.trim())       // "hello"
    println(padded.trimStart())  // "hello  "
    println(padded.trimEnd())    // "  hello"

    // 교체
    println(str.replace("World", "Kotlin")) // Hello, Kotlin!

    // 뒤집기
    println(str.reversed()) // !dlroW ,olleH
  }

  @Test
  fun 문자열_자르기() {
    val str = "Hello, World!"

    // 부분 문자열
    println(str.substring(7))      // World!
    println(str.substring(7, 12)) // World

    // 앞/뒤 N글자
    println(str.take(5))      // Hello
    println(str.takeLast(6))  // orld!  (마지막 6글자)
    println(str.drop(7))      // World!
    println(str.dropLast(1))  // Hello, World

    // 분리
    val csv = "a,b,c,d"
    println(csv.split(",")) // [a, b, c, d]
  }

  @Test
  fun 문자_판별() {
    val c1 = 'A'
    val c2 = '5'
    val c3 = ' '
    val c4 = " " // String에는 isLetter, isDigit 같은 확장함수가 없음 (Char 전용)

    // Kotlin 방식 (Char 확장함수)
    println(c1.isLetter())       // true  - 영문자
    println(c1.isUpperCase())    // true  - 대문자
    println(c1.isLowerCase())    // false - 소문자
    println(c2.isDigit())        // true  - 숫자
    println(c1.isLetterOrDigit())// true  - 영문자 또는 숫자
    println(c3.isWhitespace())   // true  - 공백

    // Java 방식 (동일한 기능, 예전 코드에서 자주 보임)
    println(Character.isLetter('A'))   // true
    println(Character.isDigit('5'))    // true
  }

  @Test
  fun 문자열_변환_타입() {
    // 숫자 변환
    println("42".toInt())       // 42
    println("3.14".toDouble())  // 3.14
    println(42.toString())      // "42"

    // 문자 <-> 숫자 (알고리즘에서 자주 사용)
    println('a' - 'a')  // 0
    println('z' - 'a')  // 25
    println('A'.code)   // 65 (ASCII)

    // Char 배열 <-> 문자열
    val chars = "hello".toCharArray()   // [h, e, l, l, o]
    val back = chars.joinToString("")   // "hello"
    println(back)
  }
}