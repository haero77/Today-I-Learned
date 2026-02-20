import org.junit.jupiter.api.Test

class 배열_정렬 {
  @Test
  fun 기본_정렬() {
    // 원본 변경 - sort()
    val arr = intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)
    arr.sort()
    println(arr.toList()) // [1, 1, 2, 3, 4, 5, 6, 9]

    // 내림차순 (원본 변경)
    arr.sortDescending()
    println(arr.toList()) // [9, 6, 5, 4, 3, 2, 1, 1]

    // 원본 유지 - sorted()
    val arr2 = arrayOf(3, 1, 4, 1, 5)
    val sorted = arr2.sorted()                // List 반환
    val sortedDesc = arr2.sortedDescending()  // List 반환
    println(sorted)     // [1, 1, 3, 4, 5]
    println(sortedDesc) // [5, 4, 3, 1, 1]
    println(arr2.toList()) // [3, 1, 4, 1, 5] (원본 유지)
  }

  @Test
  fun 문자열_배열_정렬() {
    val words = arrayOf("banana", "apple", "cherry")

    // 사전순 정렬 (원본 변경)
    words.sort()
    println(words.toList()) // [apple, banana, cherry]

    // 길이순 정렬 (원본 유지)
    val byLength = words.sortedBy { it.length }
    println(byLength) // [apple, banana, cherry]

    // 길이 역순 (원본 유지)
    val byLengthDesc = words.sortedByDescending { it.length }
    println(byLengthDesc) // [banana, cherry, apple]

    val sortedWith = words.sortedWith(compareBy({ it.length }, { it })) // 길이 먼저, 같으면 사전순
    println(sortedWith)
  }

  @Test
  fun 커스텀_정렬() {
    data class Person(val name: String, val age: Int)
    val people = arrayOf(
      Person("Alice", 30),
      Person("Bob", 25),
      Person("Alice", 25)
    )

    // 단일 조건 정렬
    people.sortBy { it.age }
    println(people.toList()) // [Bob(25), Alice(25), Alice(30)]

    // 여러 조건 정렬 (name 먼저, 같으면 age로)
    people.sortWith(compareBy({ it.name }, { it.age }))
    println(people.toList()) // [Alice(25), Alice(30), Bob(25)]

    // 역순 조건
    people.sortWith(compareByDescending<Person> { it.age }.thenBy { it.name })
    println(people.toList()) // [Alice(30), Alice(25), Bob(25)]
  }

  @Test
  fun 부분_정렬() {
    val arr = intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)

    // index 2~4(5 미포함)만 정렬
    arr.sort(fromIndex = 2, toIndex = 5)
    println(arr.toList()) // [3, 1, 1, 4, 5, 9, 2, 6]
  }

  @Test
  fun 문자_배열_정렬() {
    val chars = "dcba".toCharArray()
    chars.sort()
    println(String(chars)) // "abcd"

    // 역순
    chars.sortDescending()
    println(String(chars)) // "dcba"
  }
}