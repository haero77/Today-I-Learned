class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()

        for ((i, num2) in nums.withIndex()) {
            // num1 + num2 = target 확인.
            val num1 = target - num2
            if (map.containsKey(num1)) {
                // num 1 + num2 = target 만족 시 (num1의 인덱스, num2의 인덱스 i 반환)
                return intArrayOf(map.get(num1)!!, i)
            }
            map[num2] = i;
        }

        return intArrayOf(0, 0)
    }
}