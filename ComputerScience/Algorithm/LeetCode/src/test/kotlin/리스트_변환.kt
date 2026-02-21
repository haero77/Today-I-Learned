import org.junit.jupiter.api.Test

class 리스트_변환 {
  @Test
  fun map_변환() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // map - 각 원소 변환
    val doubled = numbers.map { it * 2 }
    println(doubled) // [2, 4, 6, 8, 10]

    val squared = numbers.map { it * it }
    println(squared) // [1, 4, 9, 16, 25]

    // mapIndexed - 인덱스와 함께 변환
    val withIndex = numbers.mapIndexed { index, value -> "$index: $value" }
    println(withIndex) // [0: 1, 1: 2, 2: 3, 3: 4, 4: 5]

    // mapNotNull - null 제외하고 변환
    val mixed = listOf("1", "2", "abc", "3")
    val parsed = mixed.mapNotNull { it.toIntOrNull() }
    println(parsed) // [1, 2, 3]
  }

  @Test
  fun filter_변환() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // filter - 조건 만족하는 원소만
    val evens = numbers.filter { it % 2 == 0 }
    println(evens) // [2, 4, 6, 8, 10]

    // filterNot - 조건 불만족하는 원소만
    val odds = numbers.filterNot { it % 2 == 0 }
    println(odds) // [1, 3, 5, 7, 9]

    // filterIndexed - 인덱스 조건
    val everyOther = numbers.filterIndexed { index, _ -> index % 2 == 0 }
    println(everyOther) // [1, 3, 5, 7, 9] (짝수 인덱스)

    // filterNotNull - null 제거
    val withNulls = listOf(1, null, 2, null, 3)
    val noNulls = withNulls.filterNotNull()
    println(noNulls) // [1, 2, 3]

    // filterIsInstance - 특정 타입만
    val mixed: List<Any> = listOf(1, "hello", 2, "world", 3.14)
    val strings = mixed.filterIsInstance<String>()
    val ints = mixed.filterIsInstance<Int>()
    println(strings) // [hello, world]
    println(ints)    // [1, 2]
  }

  @Test
  fun flatMap_변환() {
    val words = listOf("hello", "world")

    // flatMap - 각 원소를 리스트로 변환 후 평탄화
    val chars = words.flatMap { it.toList() }
    println(chars) // [h, e, l, l, o, w, o, r, l, d]

    val numbers = listOf(1, 2, 3)
    val duplicated = numbers.flatMap { listOf(it, it) }
    println(duplicated) // [1, 1, 2, 2, 3, 3]

    // flatten - 2차원 -> 1차원
    val matrix = listOf(
      listOf(1, 2, 3),
      listOf(4, 5, 6),
      listOf(7, 8, 9)
    )
    val flat = matrix.flatten()
    println(flat) // [1, 2, 3, 4, 5, 6, 7, 8, 9]
  }

  @Test
  fun 중복_제거() {
    val numbers = listOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)

    // distinct - 중복 제거
    val unique = numbers.distinct()
    println(unique) // [1, 2, 3, 4]

    // distinctBy - 특정 기준으로 중복 제거
    data class Person(val name: String, val age: Int)
    val people = listOf(
      Person("Alice", 25),
      Person("Bob", 30),
      Person("Alice", 28)  // 이름이 중복
    )
    val uniqueNames = people.distinctBy { it.name }
    println(uniqueNames) // [Alice(25), Bob(30)] - 첫 번째 Alice만
  }

  @Test
  fun 역순_셔플() {
    val list = listOf(1, 2, 3, 4, 5)

    // reversed - 역순 (새 리스트)
    val reversed = list.reversed()
    println(reversed) // [5, 4, 3, 2, 1]

    // asReversed - 역순 뷰 (원본과 연결)
    val mutable = mutableListOf(1, 2, 3, 4, 5)
    val reversedView = mutable.asReversed()
    println(reversedView) // [5, 4, 3, 2, 1]

    mutable.add(6)
    println(reversedView) // [6, 5, 4, 3, 2, 1] (원본 변경 반영)

    // shuffled - 무작위 섞기 (새 리스트)
    val shuffled = list.shuffled()
    println(shuffled) // [무작위 순서]
  }

  @Test
  fun 집계_변환() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // reduce - 누적 연산 (첫 원소부터 시작)
    val sum1 = numbers.reduce { acc, n -> acc + n }
    println(sum1) // 15

    // fold - 누적 연산 (초기값 지정)
    val sum2 = numbers.fold(0) { acc, n -> acc + n }
    println(sum2) // 15

    val sum3 = numbers.fold(10) { acc, n -> acc + n }
    println(sum3) // 25

    // runningReduce - 중간 누적 결과도 포함
    val running = numbers.runningReduce { acc, n -> acc + n }
    println(running) // [1, 3, 6, 10, 15]

    // runningFold - 초기값 포함한 중간 누적 결과
    val runningWithInit = numbers.runningFold(0) { acc, n -> acc + n }
    println(runningWithInit) // [0, 1, 3, 6, 10, 15]
  }

  @Test
  fun 조합_변환() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // associate - 리스트를 Map으로
    val map1 = numbers.associateWith { it * 2 }
    println(map1) // {1=2, 2=4, 3=6, 4=8, 5=10}

    val map2 = numbers.associateBy { it * 2 }
    println(map2) // {2=1, 4=2, 6=3, 8=4, 10=5}

    val map3 = numbers.associate { it to it * it }
    println(map3) // {1=1, 2=4, 3=9, 4=16, 5=25}

    // groupBy - 조건으로 그룹화
    val grouped = numbers.groupBy { it % 2 }
    println(grouped) // {1=[1, 3, 5], 0=[2, 4]}

    // groupingBy - 더 복잡한 그룹화
    val counts = numbers.groupingBy { it % 2 }.eachCount()
    println(counts) // {1=3, 0=2}
  }

  @Test
  fun zip_변환() {
    val names = listOf("Alice", "Bob", "Charlie")
    val ages = listOf(25, 30, 35)
    val cities = listOf("Seoul", "Busan")

    // zip - 두 리스트 결합 (짧은 쪽 기준)
    val pairs = names.zip(ages)
    println(pairs) // [(Alice, 25), (Bob, 30), (Charlie, 35)]

    val pairs2 = names.zip(cities)
    println(pairs2) // [(Alice, Seoul), (Bob, Busan)] - Charlie 제외

    // zip + transform
    val formatted = names.zip(ages) { name, age -> "$name: ${age}세" }
    println(formatted) // [Alice: 25세, Bob: 30세, Charlie: 35세]

    // unzip - Pair 리스트를 두 리스트로 분리
    val (names2, ages2) = pairs.unzip()
    println(names2) // [Alice, Bob, Charlie]
    println(ages2)  // [25, 30, 35]
  }

  @Test
  fun 타입_변환() {
    val list = listOf(1, 2, 3, 4, 5)

    // 리스트 -> 배열
    val array = list.toTypedArray()
    val intArray = list.toIntArray()

    // 리스트 -> Set
    val set = list.toSet()

    // 리스트 -> Map
    val map = list.associateWith { it * 2 }
    println(map) // {1=2, 2=4, 3=6, 4=8, 5=10}

    // 가변 <-> 불변
    val mutable = list.toMutableList()
    val immutable = mutable.toList()

    // 리스트 -> 문자열
    val joined1 = list.joinToString()
    println(joined1) // 1, 2, 3, 4, 5

    val joined2 = list.joinToString(separator = "-", prefix = "[", postfix = "]")
    println(joined2) // [1-2-3-4-5]

    val joined3 = list.joinToString(separator = " ") { "($it)" }
    println(joined3) // (1) (2) (3) (4) (5)
  }

  @Test
  fun 중첩_구조_변환() {
    // 1차원 -> 2차원
    val flat = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val matrix = flat.chunked(3)
    println(matrix) // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

    // 2차원 -> 1차원
    val flattened = matrix.flatten()
    println(flattened) // [1, 2, 3, 4, 5, 6, 7, 8, 9]

    // 복잡한 구조 변환
    data class Student(val name: String, val scores: List<Int>)
    val students = listOf(
      Student("Alice", listOf(90, 85, 88)),
      Student("Bob", listOf(78, 82, 80)),
      Student("Charlie", listOf(95, 92, 98))
    )

    // 모든 점수를 평탄화
    val allScores = students.flatMap { it.scores }
    println(allScores) // [90, 85, 88, 78, 82, 80, 95, 92, 98]

    // 평균 점수
    val averages = students.map { student ->
      student.name to student.scores.average()
    }
    println(averages) // [(Alice, 87.666...), (Bob, 80.0), (Charlie, 95.0)]
  }

  @Test
  fun 조건부_변환() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // map + if
    val categorized = numbers.map { if (it % 2 == 0) "짝수" else "홀수" }
    println(categorized)

    // partition - 두 그룹으로 분리
    val (evens, odds) = numbers.partition { it % 2 == 0 }
    println("짝수: $evens") // [2, 4, 6, 8, 10]
    println("홀수: $odds")   // [1, 3, 5, 7, 9]

    // takeIf / takeUnless - 조건 만족하면 리스트 반환, 아니면 null
    val largeList = numbers.takeIf { it.size > 5 }
    println(largeList) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    val smallList = numbers.takeUnless { it.size > 20 }
    println(smallList) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  }
}
