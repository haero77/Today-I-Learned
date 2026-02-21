import org.junit.jupiter.api.Test

class 리스트 {

  @Test
  fun 리스트_생성() {
    // 불변 리스트 (읽기 전용)
    val list1 = listOf(1, 2, 3, 4, 5)
    // list1.add(6) // 컴파일 에러!

    // 가변 리스트
    val list2 = mutableListOf(1, 2, 3)
    list2.add(4)
    list2.add(5)
    println(list2) // [1, 2, 3, 4, 5]

    // 빈 리스트
    val empty = emptyList<Int>()
    val mutableEmpty = mutableListOf<Int>()

    // 크기 지정 후 초기화
    val list3 = List(5) { 0 }        // [0, 0, 0, 0, 0]
    val list4 = List(5) { it }       // [0, 1, 2, 3, 4]
    val list5 = List(5) { it * 2 }   // [0, 2, 4, 6, 8]
    println(list3)
    println(list4)
    println(list5)
  }

  @Test
  fun 리스트_접근() {
    val list = listOf(10, 20, 30, 40, 50)

    // 인덱스 접근
    println(list[0])       // 10
    println(list.get(0))   // 10 (동일)
    println(list.first())  // 10
    println(list.last())   // 50
    println(list[list.lastIndex]) // 50

    // 안전한 접근 (없으면 null)
    println(list.getOrNull(10))      // null
    println(list.getOrElse(10) { 0 }) // 0 (기본값)

    // 크기
    println(list.size)     // 5
    println(list.isEmpty()) // false

    // 순회
    for (i in list.indices) println(list[i])
    for ((index, value) in list.withIndex()) println("$index: $value")
    for (v in list) println(v)
  }

  @Test
  fun 리스트_추가_삭제() {
    val list = mutableListOf(1, 2, 3)

    // 추가
    list.add(4)              // [1, 2, 3, 4]
    list.add(0, 0)           // [0, 1, 2, 3, 4] (인덱스 0에 추가)
    list.addAll(listOf(5, 6)) // [0, 1, 2, 3, 4, 5, 6]
    println(list)

    // 삭제
    list.remove(0)           // [1, 2, 3, 4, 5, 6] (값 0 제거)
    list.removeAt(0)         // [2, 3, 4, 5, 6] (인덱스 0 제거)
    list.removeAll(listOf(2, 4)) // [3, 5, 6]
    println(list)

    // 조건으로 삭제
    list.removeIf { it > 4 } // [3]
    println(list)

    // 전체 삭제
    list.clear()
    println(list) // []
  }

  @Test
  fun 리스트_탐색() {
    val list = listOf(3, 1, 4, 1, 5, 9, 2, 6)

    // 포함 여부
    println(list.contains(4))   // true
    println(4 in list)          // true

    // 인덱스 찾기
    println(list.indexOf(1))      // 1 (첫 번째 1의 인덱스)
    println(list.lastIndexOf(1))  // 3 (마지막 1의 인덱스)

    // 최솟값 / 최댓값
    println(list.min())  // 1
    println(list.max())  // 9

    // 합계 / 평균
    println(list.sum())     // 31
    println(list.average()) // 3.875

    // 조건 탐색
    println(list.any { it > 8 })   // true  - 8 초과 원소 존재?
    println(list.all { it > 0 })   // true  - 모든 원소가 0 초과?
    println(list.none { it < 0 })  // true  - 음수 원소 없음?
    println(list.count { it > 3 }) // 4     - 3 초과 원소 개수
    println(list.find { it > 4 })  // 5     - 조건 만족하는 첫 번째 원소
  }

  @Test
  fun 리스트_변환() {
    val list = listOf(1, 2, 3, 4, 5)

    // map - 각 원소 변환
    val doubled = list.map { it * 2 }
    println(doubled) // [2, 4, 6, 8, 10]

    // filter - 조건에 맞는 원소만
    val evens = list.filter { it % 2 == 0 }
    println(evens) // [2, 4]

    // filterNot - 조건에 맞지 않는 원소만
    val odds = list.filterNot { it % 2 == 0 }
    println(odds) // [1, 3, 5]

    // flatten - 2차원 -> 1차원
    val matrix = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
    val flat = matrix.flatten()
    println(flat) // [1, 2, 3, 4, 5, 6]

    // distinct - 중복 제거
    val duplicates = listOf(1, 2, 2, 3, 3, 3)
    println(duplicates.distinct()) // [1, 2, 3]

    // reversed - 뒤집기
    println(list.reversed()) // [5, 4, 3, 2, 1]
  }

  @Test
  fun 리스트_변경() {
    val list = mutableListOf(1, 2, 3, 4, 5)

    // 값 변경
    list[0] = 10
    println(list) // [10, 2, 3, 4, 5]

    // 뒤집기 (원본 변경)
    list.reverse()
    println(list) // [5, 4, 3, 2, 10]

    // 섞기
    list.shuffle()
    println(list) // [무작위 순서]
  }

  @Test
  fun 리스트_결합() {
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)

    // + 연산자로 결합 (새 리스트 생성)
    val combined = list1 + list2
    println(combined) // [1, 2, 3, 4, 5, 6]

    // - 연산자로 제거 (새 리스트 생성)
    val removed = combined - listOf(2, 4)
    println(removed) // [1, 3, 5, 6]

    // zip - 두 리스트 결합
    val names = listOf("Alice", "Bob", "Charlie")
    val ages = listOf(25, 30, 35)
    val pairs = names.zip(ages)
    println(pairs) // [(Alice, 25), (Bob, 30), (Charlie, 35)]

    // zipWithNext - 인접 원소끼리 결합
    val nums = listOf(1, 2, 3, 4)
    val adjacent = nums.zipWithNext()
    println(adjacent) // [(1, 2), (2, 3), (3, 4)]
  }

  @Test
  fun 리스트_변환_타입() {
    val list = listOf(1, 2, 3, 4, 5)

    // 리스트 -> 배열
    val arr = list.toTypedArray()
    val intArr = list.toIntArray()

    // 리스트 -> Set (중복 제거)
    val set = list.toSet()

    // 리스트 -> Map
    val map = list.associateWith { it * 2 }
    println(map) // {1=2, 2=4, 3=6, 4=8, 5=10}

    // 가변 <-> 불변 변환
    val mutable = list.toMutableList()
    val immutable = mutable.toList()
  }

  @Test
  fun 이차원_리스트() {
    // 2차원 리스트 생성 (3행 4열)
    val matrix = List(3) { MutableList(4) { 0 } }

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

    // flatMap으로 1차원 변환
    val flat = matrix.flatten()
    println(flat) // [1, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0]
  }
}
