class Solution {
    fun isPalindrome(s: String): Boolean {
        // start, end 포인터
        var start = 0
        var end = s.lastIndex

        // start, end 포인터 이동하면서 문자 비교
        while (start < end) {
            when {
                !s[start].isLetterOrDigit() -> start++
                !s[end].isLetterOrDigit() -> end--
                else -> {
                    if (s[start].lowercase() != s[end].lowercase()) {
                        return false
                    }
                    start++
                    end--
                }
            }
        }

        // s[start], s[end] 가 모두 일치할 경우 팰린드롬
        return true
    }
}

fun main() {
    val solution = Solution()
    solution.isPalindrome("race a car")
}