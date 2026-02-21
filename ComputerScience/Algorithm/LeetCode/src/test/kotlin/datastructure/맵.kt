package datastructure

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class 맵 {
  @Test
  fun map_선언() {
    val map1 = mutableMapOf<String, Int>()
    map1["a"] = 1
    map1["b"] = 2
    println(map1)

    map1["a"] = 100
    println(map1)
    println(map1["a"])

    val map2 = mutableMapOf(
      "key1" to "value1",
      "key2" to "value2",
    )
    println(map2)
  }

  @Test
  fun map_key_value_확인() {
    val map1 = mutableMapOf<String, Int>()
    map1["a"] = 1
    map1["b"] = 2

    // key
    assertThat(map1.containsKey("a")).isTrue()
    assertThat(map1.containsKey("c")).isFalse()

    // value
    assertThat(map1.containsValue(1)).isTrue()
    assertThat(map1.containsValue(3)).isFalse()
  }

  @Test
  fun map_key_제거() {
    val map1 = mutableMapOf<String, Int>()
    map1["a"] = 1
    map1["b"] = 2

    // remove
    map1.remove("a") // key만 일치하면 삭제
    map1.remove("b", 3) // key, value 모두 일치해야 삭제

    // key
    assertThat(map1.containsKey("a")).isFalse()
    assertThat(map1.containsKey("b")).isTrue()
  }

  @Test
  fun map_merge() {
    val map1 = mutableMapOf<String, Int>()
    map1["a"] = 1
    map1["b"] = 2

    // merge
    map1.merge("a", 1, Int::plus) // 기존값 + 1
    map1.merge("c", 1, Int::plus) // 신규값 1로 초기화

    assertThat(map1["a"]).isEqualTo(2)
    assertThat(map1.containsKey("c")).isTrue()
    assertThat(map1["c"]).isEqualTo(1)
  }
}