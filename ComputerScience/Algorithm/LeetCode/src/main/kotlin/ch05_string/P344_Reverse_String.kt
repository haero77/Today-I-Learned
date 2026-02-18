package ch05_stringclass
class Solution_ {
    fun reverseString(s: CharArray): Unit {
        var start = 0
        var end = s.size - 1

        // 서로 중앙으로 나가다 겹치는 지점에 도달하면 종료
        while (start < end) {
            // also를 이용한 우아한 스왑
            s[start] = s[end].also { s[end] = s[start] }

            // start는 한 칸 뒤로, end는 한 칸 앞으로 이동
            start++
            end--
        }
    }
}