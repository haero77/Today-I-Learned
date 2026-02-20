import org.junit.jupiter.api.Test

class 문자열_순서비교 {
  @Test
  fun 문자_순서() {
    println("'A' < 'B': ${'A' < 'B'}") // true
    println("'A' < 'a': ${'A' < 'a'}") // true (대문자가 소문자보다 먼저 옴. 소문자가 더 큼)
    println("'a' < 'B': ${'a' < 'B'}") // false

    println('z'.compareTo('a')) // 1
    println('a'.compareTo('z')) // -1
    println('a'.compareTo('a')) // 0
  }

  @Test
  fun 문자열_순서() {
    println("apple" < "banana") // true (사전 순으로 apple이 먼저 옴)
    println("Apple" < "apple") // true (사전 순으로 Apple이 먼저 옴)
    println("apple" < "app le") // false (공백이 문자보다 먼저 옴)

    println("abc".compareTo("abd"))       // 음수 -1 (abc가 앞)
    println("xyz".compareTo("abc"))       // 23. 양수 (xyz가 뒤)
    println("hello".compareTo("hello")) // 0 (동일한 문자열)

    println(" ".compareTo("a"))           // 음수 (공백(32) < 'a'(97))
    println("a ".compareTo("a"))          // 양수 (길이가 더 김)
    println("a".compareTo("a "))          // 음수 (길이가 더 짧음)

    // 공백의 ASCII 값은 32
    println("abc".compareTo("ab c"))      // 양수 ('c'(99) > ' '(32)). 'ab c'가 'abc'보다 먼저 옴
    println("ab".compareTo("ab "))        // 음수 (길이 2 vs 3, 'ab'가 더 앞)
    println("a b".compareTo("a c"))       // 음수 (' '(32) < 'c'(99))
  }
}