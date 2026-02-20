import org.junit.jupiter.api.Test

class 배열_자르기 {
  @Test
  fun 배열_slicing() {
    val arr = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    // sliceArray - 부분 배열 (Array 반환)
    val slice1 = arr.sliceArray(2..5)        // [2, 3, 4, 5]
    val slice2 = arr.sliceArray(2 until 5)   // [2, 3, 4] (5 미포함)
    println(slice1.toList())
    println(slice2.toList())

    // slice - 부분 배열 (List 반환)
    val slice3 = arr.slice(2..5)             // [2, 3, 4, 5]
    val slice4 = arr.slice(listOf(0, 3, 6))  // [0, 3, 6] (특정 인덱스만)
    println(slice3)
    println(slice4)

    // copyOfRange - 범위 복사 (Array 반환)
    val copy = arr.copyOfRange(2, 5)         // [2, 3, 4] (5 미포함)
    println(copy.toList())
  }

  @Test
  fun 배열_앞뒤_자르기() {
    val arr = arrayOf(1, 2, 3, 4, 5)

    // take - 앞에서 N개
    println(arr.take(3))      // [1, 2, 3]

    // takeLast - 뒤에서 N개
    println(arr.takeLast(2))  // [4, 5]

    // drop - 앞에서 N개 제거
    println(arr.drop(2))      // [3, 4, 5]

    // dropLast - 뒤에서 N개 제거
    println(arr.dropLast(2))  // [1, 2, 3]
  }

  @Test
  fun 배열_조건부_자르기() {
    val arr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // takeWhile - 조건 만족하는 동안 가져오기
    val result1 = arr.takeWhile { it < 5 }
    println(result1) // [1, 2, 3, 4]

    // dropWhile - 조건 만족하는 동안 제거
    val result2 = arr.dropWhile { it < 5 }
    println(result2) // [5, 6, 7, 8, 9]

    // takeLastWhile - 뒤에서부터 조건 만족하는 동안
    val result3 = arr.takeLastWhile { it > 5 }
    println(result3) // [6, 7, 8, 9]

    // dropLastWhile - 뒤에서부터 조건 만족하는 동안 제거
    val result4 = arr.dropLastWhile { it > 5 }
    println(result4) // [1, 2, 3, 4, 5]
  }

  @Test
  fun 배열_분할() {
    val arr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // chunked - N개씩 묶기 (List로 변환 필요)
    val chunked = arr.toList().chunked(3)
    println(chunked) // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

    // windowed - 슬라이딩 윈도우 (List로 변환 필요)
    val windowed = arr.toList().windowed(size = 3, step = 1)
    println(windowed) // [[1, 2, 3], [2, 3, 4], [3, 4, 5], ...]

    val windowed2 = arr.toList().windowed(size = 3, step = 2)
    println(windowed2) // [[1, 2, 3], [3, 4, 5], [5, 6, 7], [7, 8, 9]]

    // partition - 조건으로 두 그룹 나누기
    val (evens, odds) = arr.partition { it % 2 == 0 }
    println("짝수: $evens") // [2, 4, 6, 8]
    println("홀수: $odds")   // [1, 3, 5, 7, 9]
  }

  @Test
  fun IntArray_자르기() {
    val arr = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    // IntArray도 동일하게 사용 가능
    val slice = arr.sliceArray(2..5)
    println(slice.toList()) // [2, 3, 4, 5]

    val copy = arr.copyOfRange(2, 5)
    println(copy.toList()) // [2, 3, 4]

    // take, drop도 동일
    println(arr.take(3))     // [1, 2, 3]
    println(arr.drop(2))     // [2, 3, 4, 5, 6, 7, 8, 9]
  }
}