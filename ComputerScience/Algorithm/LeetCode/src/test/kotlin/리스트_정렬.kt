import org.junit.jupiter.api.Test

class 리스트_정렬 {
  @Test
  fun 기본_정렬() {
    val list = listOf(3, 1, 4, 1, 5, 9, 2, 6)

    // sorted - 오름차순 (새 리스트 반환)
    val sorted = list.sorted()
    println(sorted) // [1, 1, 2, 3, 4, 5, 6, 9]
    println(list)   // [3, 1, 4, 1, 5, 9, 2, 6] (원본 유지)

    // sortedDescending - 내림차순 (새 리스트 반환)
    val sortedDesc = list.sortedDescending()
    println(sortedDesc) // [9, 6, 5, 4, 3, 2, 1, 1]

    // MutableList는 원본 변경 가능
    val mutableList = mutableListOf(3, 1, 4, 1, 5, 9, 2, 6)
    mutableList.sort()  // 원본 변경
    println(mutableList) // [1, 1, 2, 3, 4, 5, 6, 9]

    mutableList.sortDescending()  // 원본 변경
    println(mutableList) // [9, 6, 5, 4, 3, 2, 1, 1]
  }

  @Test
  fun 문자열_리스트_정렬() {
    val words = listOf("banana", "apple", "cherry")

    // 사전순 정렬
    val sorted = words.sorted()
    println(sorted) // [apple, banana, cherry]

    // 길이순 정렬
    val byLength = words.sortedBy { it.length }
    println(byLength) // [apple, banana, cherry]

    // 길이 역순 정렬
    val byLengthDesc = words.sortedByDescending { it.length }
    println(byLengthDesc) // [banana, cherry, apple]

    // 대소문자 무시 정렬
    val mixed = listOf("Zebra", "apple", "Banana")
    val caseInsensitive = mixed.sortedWith(String.CASE_INSENSITIVE_ORDER)
    println(caseInsensitive) // [apple, Banana, Zebra]
  }

  @Test
  fun 커스텀_정렬() {
    data class Person(val name: String, val age: Int)
    val people = listOf(
      Person("Alice", 30),
      Person("Bob", 25),
      Person("Alice", 25)
    )

    // 단일 조건 정렬
    val byAge = people.sortedBy { it.age }
    println(byAge) // [Bob(25), Alice(25), Alice(30)]

    // 여러 조건 정렬 (name 먼저, 같으면 age로)
    val byNameAndAge = people.sortedWith(compareBy({ it.name }, { it.age }))
    println(byNameAndAge) // [Alice(25), Alice(30), Bob(25)]

    // 역순 조건
    val byAgeDesc = people.sortedWith(compareByDescending<Person> { it.age }.thenBy { it.name })
    println(byAgeDesc) // [Alice(30), Alice(25), Bob(25)]
  }

  @Test
  fun 복잡한_정렬() {
    val words = listOf("cat", "banana", "ant", "apple", "dog")

    // 여러 조건: 길이 먼저, 같으면 사전순
    val sorted = words.sortedWith(compareBy({ it.length }, { it }))
    println(sorted) // [ant, cat, dog, apple, banana]

    // 짝수/홀수 분리 후 각각 정렬
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val customSort = numbers.sortedWith(compareBy<Int> { it % 2 }.thenBy { it })
    println(customSort) // [2, 4, 6, 8, 1, 3, 5, 7, 9] (짝수 먼저, 각각 오름차순)
  }

  @Test
  fun 부분_정렬() {
    val list = listOf(3, 1, 4, 1, 5, 9, 2, 6)

    // 상위 N개 (가장 작은 N개)
    val top3 = list.sorted().take(3)
    println(top3) // [1, 1, 2]

    // 하위 N개 (가장 큰 N개)
    val bottom3 = list.sortedDescending().take(3)
    println(bottom3) // [9, 6, 5]

    // 특정 범위만 정렬 (MutableList 필요)
    val mutable = list.toMutableList()
    val subList = mutable.subList(2, 5)  // index 2~4
    subList.sort()
    println(mutable) // [3, 1, 1, 4, 5, 9, 2, 6]
  }

  @Test
  fun 안정_정렬() {
    data class Item(val name: String, val priority: Int, val order: Int)
    val items = listOf(
      Item("A", 1, 1),
      Item("B", 2, 2),
      Item("C", 1, 3),  // priority가 A와 같음
      Item("D", 2, 4)   // priority가 B와 같음
    )

    // sortedBy는 안정 정렬 (같은 값일 때 원래 순서 유지)
    val sorted = items.sortedBy { it.priority }
    sorted.forEach { println("${it.name} - order: ${it.order}") }
    // A - order: 1
    // C - order: 3 (A와 priority 같지만 원래 순서 유지)
    // B - order: 2
    // D - order: 4
  }

  @Test
  fun 정렬_성능() {
    // 큰 리스트의 경우
    val largeList = (1..1000000).shuffled()

    // sorted() - 새 리스트 생성 (안전)
    val sorted1 = largeList.sorted()

    // toMutableList().apply { sort() } - 가변 변환 후 정렬 (메모리 효율적)
    val sorted2 = largeList.toMutableList().apply { sort() }

    println("정렬 완료")
  }
}
