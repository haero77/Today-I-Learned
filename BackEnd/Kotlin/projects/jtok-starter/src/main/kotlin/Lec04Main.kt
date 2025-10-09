fun main() {
    val money1 = Money(1000)
    val money2 = Money(2000)

    val money3 = money1 + money2
    println(money3.amount) // 3000 글세 어느정도 이게 보전이되면 그게 좋긴 하
}