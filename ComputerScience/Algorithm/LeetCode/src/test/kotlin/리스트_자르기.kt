import org.junit.jupiter.api.Test

class 리스트_자르기 {
  @Test
  fun 리스트_slicing() {
    val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    // slice - 부분 리스트
    val slice1 = list.slice(2..5)            // [2, 3, 4, 5]
    val slice2 = list.slice(2 until 5)       // [2, 3, 4] (5 미포함)
    val slice3 = list.slice(listOf(0, 3, 6)) // [0, 3, 6] (특정 인덱스만)
    println(slice1)
    println(slice2)
    println(slice3)

    // subList - 부분 리스트 뷰 (원본과 연결됨)
    val mutableList = mutableListOf(0, 1, 2, 3, 4, 5)
    val subList = mutableList.subList(2, 5)  // [2, 3, 4] (5 미포함)
    println(subList)

    // subList 수정 시 원본도 변경됨!
    subList[0] = 99
    println(mutableList) // [0, 1, 99, 3, 4, 5]ㅋ
  }

  @Test
  fun 리스트_앞뒤_자르기() {
    val list = listOf(1, 2, 3, 4, 5)

    // take - 앞에서 N개
    println(list.take(3))      // [1, 2, 3]

    // takeLast - 뒤에서 N개
    println(list.takeLast(2))  // [4, 5]

    // drop - 앞에서 N개 제거
    println(list.drop(2))      // [3, 4, 5]

    // dropLast - 뒤에서 N개 제거
    println(list.dropLast(2))  // [1, 2, 3]
  }

  @Test
  fun 리스트_조건부_자르기() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // takeWhile - 조건 만족하는 동안 가져오기
    val result1 = list.takeWhile { it < 5 }
    println(result1) // [1, 2, 3, 4]

    // dropWhile - 조건 만족하는 동안 제거
    val result2 = list.dropWhile { it < 5 }
    println(result2) // [5, 6, 7, 8, 9]

    // takeLastWhile - 뒤에서부터 조건 만족하는 동안
    val result3 = list.takeLastWhile { it > 5 }
    println(result3) // [6, 7, 8, 9]

    // dropLastWhile - 뒤에서부터 조건 만족하는 동안 제거
    val result4 = list.dropLastWhile { it > 5 }
    println(result4) // [1, 2, 3, 4, 5]
  }

  @Test
  fun 리스트_분할() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // chunked - N개씩 묶기
    val chunked = list.chunked(3)
    println(chunked) // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

    // chunked + transform - 묶으면서 변환
    val chunkedSum = list.chunked(3) { it.sum() }
    println(chunkedSum) // [6, 15, 24]

    // windowed - 슬라이딩 윈도우
    val windowed = list.windowed(size = 3, step = 1)
    println(windowed) // [[1, 2, 3], [2, 3, 4], [3, 4, 5], ...]

    val windowed2 = list.windowed(size = 3, step = 2)
    println(windowed2) // [[1, 2, 3], [3, 4, 5], [5, 6, 7], [7, 8, 9]]

    // windowed + transform
    val windowedSum = list.windowed(size = 3, step = 1) { it.sum() }
    println(windowedSum) // [6, 9, 12, 15, 18, 21, 24]

    // partition - 조건으로 두 그룹 나누기
    val (evens, odds) = list.partition { it % 2 == 0 }
    println("짝수: $evens") // [2, 4, 6, 8]
    println("홀수: $odds")   // [1, 3, 5, 7, 9]
  }

  @Test
  fun 리스트_그룹화() {
    val words = listOf("apple", "banana", "ant", "cherry", "cat", "dog")

    // groupBy - 조건으로 그룹화 (Map 반환)
    val byLength = words.groupBy { it.length }
    println(byLength)
    // {5=[apple], 6=[banana, cherry], 3=[ant, cat, dog]}

    val byFirstChar = words.groupBy { it.first() }
    println(byFirstChar)
    // {a=[apple, ant], b=[banana], c=[cherry, cat], d=[dog]}

    // groupBy + transform
    val grouped = words.groupBy({ it.length }, { it.uppercase() })
    println(grouped)
    // {5=[APPLE], 6=[BANANA, CHERRY], 3=[ANT, CAT, DOG]}
  }

  @Test
  fun 리스트_구간_나누기() {
    val numbers = listOf(1, 5, 3, 8, 2, 9, 4)

    // zipWithNext - 인접 원소끼리 묶기
    val pairs = numbers.zipWithNext()
    println(pairs) // [(1, 5), (5, 3), (3, 8), (8, 2), (2, 9), (9, 4)]

    // zipWithNext + transform
    val diffs = numbers.zipWithNext { a, b -> b - a }
    println(diffs) // [4, -2, 5, -6, 7, -5]

    // windowed(2) vs zipWithNext
    val windowed = numbers.windowed(2)
    println(windowed) // [[1, 5], [5, 3], [3, 8], [8, 2], [2, 9], [9, 4]]
  }

  @Test
  fun 리스트_특정_범위() {
    val list = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90)

    // filterIndexed - 인덱스 조건으로 필터링
    val everyOther = list.filterIndexed { index, _ -> index % 2 == 0 }
    println(everyOther) // [10, 30, 50, 70, 90] (짝수 인덱스)

    // slice로 특정 인덱스만
    val specific = list.slice(listOf(0, 2, 4, 6, 8))
    println(specific) // [10, 30, 50, 70, 90]

    // 특정 범위 제외
    val excluded = list.filterIndexed { index, _ -> index !in 3..6 }
    println(excluded) // [10, 20, 30, 80, 90]
  }

  @Test
  fun 중첩_리스트_자르기() {
    val matrix = listOf(
      listOf(1, 2, 3),
      listOf(4, 5, 6),
      listOf(7, 8, 9)
    )

    // 특정 행만
    val row = matrix[1]
    println(row) // [4, 5, 6]

    // 특정 열만 (map 활용)
    val column = matrix.map { it[1] }
    println(column) // [2, 5, 8]

    // 부분 행렬
    val subMatrix = matrix.slice(0..1).map { it.slice(1..2) }
    println(subMatrix) // [[2, 3], [5, 6]]
  }

  @Test
  fun 무한_시퀀스_자르기() {
    // 무한 시퀀스 생성
    val infiniteNumbers = generateSequence(1) { it + 1 }

    // take로 제한
    val first10 = infiniteNumbers.take(10).toList()
    println(first10) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    // takeWhile로 조건 제한
    val lessThan100 = infiniteNumbers.takeWhile { it < 100 }.toList()
    println(lessThan100.size) // 99

    // chunked + take
    val chunks = infiniteNumbers.chunked(3).take(3).toList()
    println(chunks) // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
  }

  @Test
  fun 리스트_합치기() {
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)
    val list3 = listOf(7, 8, 9)

    // + 연산자로 합치기 (새 리스트 생성)
    val combined1 = list1 + list2
    println(combined1) // [1, 2, 3, 4, 5, 6]

    val combined2 = list1 + list2 + list3
    println(combined2) // [1, 2, 3, 4, 5, 6, 7, 8, 9]

    // plus - + 연산자와 동일
    val combined3 = list1.plus(list2)
    println(combined3) // [1, 2, 3, 4, 5, 6]

    // flatten - 2차원 리스트를 1차원으로
    val nested = listOf(list1, list2, list3)
    val flattened = nested.flatten()
    println(flattened) // [1, 2, 3, 4, 5, 6, 7, 8, 9]
  }

  @Test
  fun 가변_리스트_합치기() {
    val mutable1 = mutableListOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)

    // addAll - 원본에 추가 (원본 변경)
    mutable1.addAll(list2)
    println(mutable1) // [1, 2, 3, 4, 5, 6]

    // += 연산자 (원본 변경)
    val mutable2 = mutableListOf(1, 2, 3)
    mutable2 += listOf(4, 5, 6)
    println(mutable2) // [1, 2, 3, 4, 5, 6]

    // 특정 위치에 추가
    val mutable3 = mutableListOf(1, 2, 5, 6)
    mutable3.addAll(2, listOf(3, 4))  // index 2에 삽입
    println(mutable3) // [1, 2, 3, 4, 5, 6]
  }

  @Test
  fun zip_합치기() {
    val names = listOf("Alice", "Bob", "Charlie")
    val ages = listOf(25, 30, 35)
    val cities = listOf("Seoul", "Busan")

    // zip - 두 리스트를 Pair로 결합 (짧은 쪽 기준)
    val pairs = names.zip(ages)
    println(pairs) // [(Alice, 25), (Bob, 30), (Charlie, 35)]

    val pairs2 = names.zip(cities)
    println(pairs2) // [(Alice, Seoul), (Bob, Busan)] - Charlie 제외

    // zip + transform
    val formatted = names.zip(ages) { name, age -> "$name: ${age}세" }
    println(formatted) // [Alice: 25세, Bob: 30세, Charlie: 35세]

    // zipWithNext - 인접 원소끼리 결합
    val numbers = listOf(1, 2, 3, 4, 5)
    val adjacent = numbers.zipWithNext()
    println(adjacent) // [(1, 2), (2, 3), (3, 4), (4, 5)]
  }

  @Test
  fun 여러_리스트_합치기() {
    val lists = listOf(
      listOf(1, 2),
      listOf(3, 4),
      listOf(5, 6),
      listOf(7, 8)
    )

    // flatten - 모든 리스트 합치기
    val all = lists.flatten()
    println(all) // [1, 2, 3, 4, 5, 6, 7, 8]

    // reduce로 합치기
    val combined = lists.reduce { acc, list -> acc + list }
    println(combined) // [1, 2, 3, 4, 5, 6, 7, 8]

    // fold로 합치기 (초기값 지정 가능)
    val withInit = lists.fold(listOf(0)) { acc, list -> acc + list }
    println(withInit) // [0, 1, 2, 3, 4, 5, 6, 7, 8]
  }

  @Test
  fun 교대로_합치기() {
    val list1 = listOf(1, 3, 5, 7, 9)
    val list2 = listOf(2, 4, 6, 8, 10)

    // zip + flatMap으로 교대로 합치기
    val interleaved = list1.zip(list2).flatMap { listOf(it.first, it.second) }
    println(interleaved) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    // 수동으로 교대 합치기
    val manual = mutableListOf<Int>()
    for (i in 0 until maxOf(list1.size, list2.size)) {
      if (i < list1.size) manual.add(list1[i])
      if (i < list2.size) manual.add(list2[i])
    }
    println(manual) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  }

  @Test
  fun 중복_제거하며_합치기() {
    val list1 = listOf(1, 2, 3, 4)
    val list2 = listOf(3, 4, 5, 6)
    val list3 = listOf(5, 6, 7, 8)

    // union - 합집합 (중복 제거)
    val union1 = list1.union(list2)
    println(union1) // [1, 2, 3, 4, 5, 6]

    // + 후 distinct
    val union2 = (list1 + list2 + list3).distinct()
    println(union2) // [1, 2, 3, 4, 5, 6, 7, 8]

    // toSet으로 중복 제거
    val union3 = (list1 + list2 + list3).toSet()
    println(union3) // [1, 2, 3, 4, 5, 6, 7, 8]
  }

  @Test
  fun 조건부_합치기() {
    val positives = listOf(1, 2, 3)
    val negatives = listOf(-1, -2, -3)
    val zeros = listOf(0, 0)

    // if 조건으로 선택적 합치기
    fun combine(includeNegative: Boolean, includeZero: Boolean): List<Int> {
      var result = positives
      if (includeNegative) result = result + negatives
      if (includeZero) result = result + zeros
      return result
    }

    println(combine(true, false))  // [1, 2, 3, -1, -2, -3]
    println(combine(false, true))  // [1, 2, 3, 0, 0]
    println(combine(true, true))   // [1, 2, 3, -1, -2, -3, 0, 0]
  }
}
