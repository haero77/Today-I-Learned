import org.junit.jupiter.api.Test

class also {
  @Test
  fun also_() {
    val numbers = mutableListOf(1, 2, 3)
    numbers
      .add(4)
      .also { println("$it") } // true - add()의 반환값이 true이므로 it은 true

    val numbers2 = mutableListOf(1, 2, 3)
    numbers2
      .also { println("$it") } // [1, 2, 3] - also 블록이 실행되기 전에 numbers2의 현재 상태를 출력
      .add(4)
  }

  @Test
  fun also_문자_치환() {
    val arr = charArrayOf('A', 'B')
    arr[0] = arr[1].also {arr[1] = arr[0] } // arr[0] = 'B', arr[1] = 'A'로 스왑
    println(arr) // BA - charArray

    val arr2 = charArrayOf('A', 'B')
    val reversed = arr2.reversed() // List<Char>로 반환
    println(reversed) // [B, A] - List<Char>
  }
}