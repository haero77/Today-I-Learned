class Solution {
    fun isPalindrome(s: String): Boolean {
        var start = 0
        var end = s.length - 1

        // 서로 중앙으로 만나다가 겹치는 부분 있으면 종료
        while (start < end) {
            when {
                // 영숫자 판별 및 유효하지 않으면 뒤로 한 칸 이동
                !Character.isLetterOrDigit(s[start]) -> start++
                // 영숫자 판별 및 유효하지 않으면 앞으로로 한 칸 이동
                !Character.isLetterOrDigit(s[end]) -> end--
                else -> {
                    // 이 외에는 유효한 문자열이므로 앞뒤 글자를 모두 소문자로 변경하여 비교
                    if (s[start].lowercase() != s[end].lowercase()) {
                        return false
                    }
                    // 앞쪽 문자 한 칸 뒤 이동, 뒤쪽 문자 한 칸 앞 이동.
                    start++
                    end--
                }
            }
        }

        return true
    }
}