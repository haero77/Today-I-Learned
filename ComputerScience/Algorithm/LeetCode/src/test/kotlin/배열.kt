import org.junit.jupiter.api.Test

class 배열 {

  @Test
  fun 배열_생성() {
    // 기본 생성
    val arr1 = arrayOf(1, 2, 3, 4, 5)

    // 크기 지정 후 초기화
    val arr2 = Array(5) { 0 }        // [0, 0, 0, 0, 0]
    val arr3 = Array(5) { it }       // [0, 1, 2, 3, 4] (인덱스로 초기화)
    val arr4 = Array(5) { it * 2 }   // [0, 2, 4, 6, 8]

    // 원시타입 배열 (boxing 없이 성능 유리)
    val intArr = IntArray(5)         // [0, 0, 0, 0, 0]
    val intArr2 = intArrayOf(1, 2, 3)
  }

  @Test
  fun 배열_접근() {
    val arr = arrayOf(10, 20, 30, 40, 50)

    // 인덱스 접근
    println(arr[0])       // 10
    println(arr.first())  // 10
    println(arr.last())   // 50
    println(arr[arr.lastIndex]) // 50

    // 길이
    println(arr.size)     // 5

    // 범위 순회
    for (i in arr.indices) println(arr[i])
    for ((index, value) in arr.withIndex()) println("$index: $value")
    for (v in arr) println(v)
  }

  @Test
  fun 배열_탐색() {
    val arr = arrayOf(3, 1, 4, 1, 5, 9, 2, 6)

    // 포함 여부
    println(arr.contains(4))   // true
    println(4 in arr)          // true (contains와 동일)

    // 인덱스 찾기
    println(arr.indexOf(1))      // 1 (첫 번째 1의 인덱스)
    println(arr.lastIndexOf(1))  // 3 (마지막 1의 인덱스)

    // 최솟값 / 최댓값
    println(arr.min())  // 1
    println(arr.max())  // 9

    // 합계 / 평균
    println(arr.sum())     // 31
    println(arr.average()) // 3.875

    // 조건 탐색
    println(arr.any { it > 8 })   // true  - 8 초과 원소 존재?
    println(arr.all { it > 0 })   // true  - 모든 원소가 0 초과?
    println(arr.none { it < 0 })  // true  - 음수 원소 없음?
    println(arr.count { it > 3 }) // 3     - 3 초과 원소 개수
    println(arr.find { it > 4 })  // 5     - 조건 만족하는 첫 번째 원소
  }

  @Test
  fun 배열_정렬() {
    val arr = intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)

    // 정렬 (원본 변경)
    arr.sort()
    println(arr.toList()) // [1, 1, 2, 3, 4, 5, 6, 9]

    // 내림차순 (원본 변경)
    arr.sortDescending()
    println(arr.toList()) // [9, 6, 5, 4, 3, 2, 1, 1]

    // 원본 유지하고 정렬된 새 배열 반환
    val arr2 = arrayOf(3, 1, 4, 1, 5)
    val sorted = arr2.sorted()           // 오름차순 List 반환
    val sortedDesc = arr2.sortedDescending() // 내림차순 List 반환

    // 커스텀 정렬
    val words = arrayOf("banana", "apple", "cherry")
    val sortedByLength = words.sortedBy { it.length } // 길이 기준 오름차순
    println(sortedByLength) // [apple, banana, cherry]
  }

  @Test
  fun 배열_변환() {
    val arr = arrayOf(1, 2, 3, 4, 5)

    // map - 각 원소 변환
    val doubled = arr.map { it * 2 }
    println(doubled) // [2, 4, 6, 8, 10]

    // filter - 조건에 맞는 원소만
    val evens = arr.filter { it % 2 == 0 }
    println(evens) // [2, 4]

    // flatten - 2차원 -> 1차원
    val matrix = arrayOf(arrayOf(1, 2), arrayOf(3, 4), arrayOf(5, 6))
    val flat = matrix.flatten()
    println(flat) // [1, 2, 3, 4, 5, 6]

    // 배열 -> 리스트 / 리스트 -> 배열
    val list = arr.toList()
    val backToArr = list.toTypedArray()
  }

  @Test
  fun 문자_배열() {
    // CharArray 생성
    val chars1 = charArrayOf('a', 'b', 'c')
    val chars2 = CharArray(5) { 'a' }          // ['a', 'a', 'a', 'a', 'a']
    val chars3 = CharArray(5) { 'a' + it }     // ['a', 'b', 'c', 'd', 'e']

    // 문자열 <-> CharArray 변환 (알고리즘에서 자주 사용)
    val str = "hello"
    val fromStr = str.toCharArray()            // ['h', 'e', 'l', 'l', 'o']
    val backToStr = fromStr.joinToString("")   // "hello"
    val backToStr2 = String(fromStr)           // "hello" (더 간결)

    // 문자 정렬
    val unsorted = "dcba".toCharArray()
    unsorted.sort()
    println(String(unsorted)) // "abcd"

    // 문자 판별
    for (c in chars3) {
      println("$c: isLetter=${c.isLetter()}, isUpperCase=${c.isUpperCase()}")
    }
  }

  @Test
  fun 문자열_배열() {
    // String 배열 생성
    val words = arrayOf("apple", "banana", "cherry")
    val empty = Array(3) { "" }

    // 접근
    println(words[0])        // apple
    println(words.first())   // apple
    println(words.last())    // cherry

    // 정렬
    words.sort()                              // 사전순 오름차순 (원본 변경)
    println(words.toList())                   // [apple, banana, cherry]

    val byLength = words.sortedBy { it.length }       // 길이순
    val byLengthDesc = words.sortedByDescending { it.length } // 길이 역순

    // 탐색
    println("banana" in words)               // true
    println(words.indexOf("banana"))         // 1

    // 변환
    val lengths = words.map { it.length }    // [5, 6, 6]
    val longWords = words.filter { it.length > 5 } // [banana, cherry]
    val joined = words.joinToString(", ")    // "apple, banana, cherry"

    println(lengths)
    println(longWords)
    println(joined)
  }

  @Test
  fun 이차원_배열() {
    // 2차원 배열 생성 (3행 4열)
    val matrix = Array(3) { IntArray(4) { 0 } }

    // 값 설정 및 접근
    matrix[0][0] = 1
    matrix[1][2] = 5
    println(matrix[1][2]) // 5

    // 행/열 크기
    println(matrix.size)     // 3 (행)
    println(matrix[0].size)  // 4 (열)

    // 순회
    for (row in matrix) {
      for (value in row) print("$value ")
      println()
    }
  }
}