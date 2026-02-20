package sort

import org.junit.jupiter.api.Test

class BubbleSort {
  /**
   * 의사 코드
   * n <- length of array
   * for i from 0 to n-1: // i: 전체 정렬을 위해 필요한 총 라운드 수
   *    for j from 0 to n-2-i:
   *      if arr[j] > arr[j+1]:
   *         swap (arr[j], arr[j+1])
   */
  @Test
  fun bubbleSort() {
    val arr = intArrayOf(5, 4, 3, 2, 1)
    // 가장 큰 거를 오른쪽으로 밀고, 그 다음 큰 거를 오른쪽으로 밀고, ..

    for (i in arr.indices) {
      for (j in 0..arr.size - 2 - i) {
        if (arr[j] > arr[j + 1]) {
          val temp = arr[j]
          arr[j] = arr[j+1]
          arr[j+1] = temp
        }
      }
    }

    println(arr.toList())

    /*
    for (i in arr.indices) {
    var swapped = false // 이번 라운드에 자리 바꿈이 있었나?

    for (j in 0..arr.size - 2 - i) {
        if (arr[j] > arr[j + 1]) {
            val temp = arr[j]
            arr[j] = arr[j + 1]
            arr[j + 1] = temp
            swapped = true // 자리를 바꿨음!
        }
    }

    // 만약 한 번도 자리를 안 바꿨다면? 이미 정렬 완료!
    if (!swapped) break
}
     */
  }
}