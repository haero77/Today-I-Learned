> 원글: https://www.inflearn.com/community/questions/891531

예외처리에 대한 계층간 역할 구분에 대해서 질문해 주셨는데요!! 당연히 정답이 있는 문제는 아니기에 제가 선호하는 방식을 말씀드려 보겠습니다!



모든 예외에 대한 구분은 아닐 수 있지만, 대략적으로 구분해보면 다음과 같이 나눠지는 것 같습니다.

비즈니스와 관련된 예외 또는 Service 계층에서만 처리할 수 있는 예외
특정 SQL에 대하여 단순히 데이터가 없거나 있음을 나타내는 예외


[1. 서비스와 관련된 예외]

서비스와 관련된 예외의 예시로는

휴면 회원이 로그인 시도를 했다거나
배송이 불가능한 지역에서 주문이 들어왔다거나
하는 예시가 있을 것 같습니다.

이런 경우는 Repository에서도 처리가 가능하긴 해요! 예를 들어, 휴면 회원이 로그인 시도를 하면 다음과 같은 두 가지 방식으로 구현이 가능하죠

// 방식 1 - Service
fun login(userId: Long) {
val user = userRepository.findByIdOrThrow(userId)
if (user.isSleep) {
throw .... // 예외 던지기!
}
}
// 방식 2
// Service 코드
fun login(userId: Long) {
val user = userRepository.findNotSleepUser(userId)
// 여기 아래서는 SLEEP 유저가 아니라고 생각할 수 있음.
}

// Repository 코드
fun findNotSleepUser(userId: Long): User {
// SQL을 DB에 날린다! 예를 들면 이런 SQL을 날릴 수 있다.
select * from user where status != 'SLEEP' and id = ?
// 그 후 SQL 결과가 없다면 바로 예뢰를 발생시킨다.
}
이런 경우, 저는 1번 방식 - Service 에서 처리하는 방식을 선호합니다!

그 이유는 비즈니스와 관련된 예외는 IllgalArgumentException / IllegalStateException과 같은 표준 예외를 넘어선 예외 처리가 필요할 수 있기 때문입니다.

예를 들면, custom exception class를 만들 수도 있고, 표준화된 예외 class와 Exception Enum code의 조합을 사용할 수도 있죠! 이런 경우 보통 비즈니스와 관련된 예외를 뱉게 되고, Client에게도 영향을 주기 때문에, 어떤 Exception 종류를 어떻게 반환하는지에 따라 유저에게 전해지는 예외 메시지가 달라질 수도 있습니다. 즉 조금 더 사용자와 가깝다고 할 수 있죠.

또한, 이렇게 코드가 service 쪽에 들어가게 되면 "비즈니스 로직"이라 불리는 기능들이 SQL 영역이 아니라 Object 영역으로 많이 넘어올 수 있게 됩니다. 저희가 JPA와 같은 ORM을 주로 사용하는 이유이기도 하죠. 그럼 단위 테스트도 훨씬 용이해지고, SQL에 많은 로직이 들어간 것 보다 유지보수도 용이해집니다.

위의 코드로 예를 들어보겠습니다.

자 이제 SLEEP 상태의 유저만 로그인을 못하는 것이 아니라, 강퇴당한(?) 유저도 로그인을 하지 못한다 라고 해보겠습니다. 만약 1번 형태로 코드를 작성했다면, user.isSleep || user.isDroppedOut 정도로 코드를 작성해주거나 아예 User 도메인에

class User {
val canLogIn: Boolean
get() = user.status != SLEEP && user.status != DROPPED_OUT
}
과 같은 코드를 작성해 다른 개발자가 "아~~ 휴면상태, 강퇴상태에는 로그인을 할 수 없구나~"라는 것을 알 수 있게 만들어 줄 수 있고~ User.canLogIn 이라는 프로퍼티를 테스트에 진정한 의미의 단위 테스트를 쉽게 작성할 수도 있습니다.

하지만 2번 형태로 코드를 작성했다면 조~금 애매해집니다! 당연히 구현상 Repository에 코드 수정이 이루어져야 하는데 우선 함수 이름부터 바꿔야 합니다. (즉 이 함수를 쓰는 모든 곳에 수정이 필요하죠. 여차하면 함수가 쪼개질 수도 있습니다) 그리고 SQL도 다시 한 번 변경해주어야 하고요! not in이 들어가는 복잡한 SQL이 될 겁니다. 유저의 로그인에 관한 도메인 지식 역시 이 Repository 코드와 Service 코드를 모두 함께 살펴보아야 어떤 경우에 로그인을 할 수 있고~ 어떤 경우에 로그인을 할 수 없는지 알게 됩니다.

그래서 이런 유형은 Repository 계층으로 예외 처리 역할을 넘기기 보다는 Service 단에서 제어하는 것을 선호하는 편입니다.



[2 - Service 계층에서만 처리할 수 있는 예외]

어떤 상황에서는 애당초 Service 계층에서만 예외를 처리할 수도 있습니다. Repository 영역을 넘어사는기술적인 부분이라거나 (대표적으로는 동시성 처리가 생각나네요!) 여러 도메인에 걸친 조회가 필요할 수도 있겠죠

이런 경우는 선택의 여지 없이 Service 계층에서 예외를 처리하게 됩니다.



[3 - 특정 SQL에 대해 단순히 데이터가 있거나 없음을 나타내는 경우]

이런 case는, 강의에서도 extension function으로 만들었던 findByIdOrThrow() 가 있습니다.

정말 단순히 특정 조건으로 데이터가 있다면 가져오고 싶은거죠!

만약 Querydsl을 활용한다면 꼭 findById 가 아니라 말씀해주신 findByName() 에서도 사용할 수 있게 됩니다.

// Repository에서 예외를 바로 던지는 코드의 느낌
fun findByName(name: String): User {
return queryFactory.select(user)
.from(user)
.where(
user.name.eq(name),
)
.fetchOne() ?: fail()
}
그리고 이런 경우, 저는 Repository에서 예외를 던져주는 것을 선호합니다.

만약 무조건 가져오고 싶은 함수와 있는 경우에만 가져오고 싶은 함수가 모두 필요하다면, 아래와 같이 함수를 2개 만들어 사용하기도 합니다.

fun findByName(name: String): User {
return findByNameOrNull(name) ?: fail()
}

fun findByNameOrNull(name: String): User {
return queryFactory.select(user)
.from(user)
.where(
user.name.eq(name),
)
.fetchOne()
}
사실 "Repository에서는 무조건 데이터를 주거나 null을 주어야 하고, 모든 예외 처리는 Service에서 이루어지는 것이 계층간 역할을 더 잘 표현한다"고 생각하시는 분들도 꽤 계십니다! 그리고 이러한 의견도 충분히 일리 있다고 생각합니다.

다만, 저 같은 경우는 반복되는 null 처리를 Service에서 줄이고는 싶고~ 그렇다고 아래와 같이 Service 간의 계층을 하나 더 만들자니~~

Controller가 호출하는 Service (아래 Service를 호출)
가장 기본적인 조회 + null 처리를 하는 Service
애매하다고 생각해서 Repository에서 비즈니스와 무관한 아주 단순한 예외 처리는 바로 해주는 편입니다 ㅎㅎㅎ Service 간의 계층이 애매하다고 생각하는 이유는, 어차피 비즈니스가 복잡해지면 Service 간의 계층이 생기게 되거든요! Facade 패턴을 사용하기도 하고~ 아니면 로직의 중복을 제어하기 위해 여러 계층을 만들기도 하죠. 이런데 기본적인 조회 + null 처리를 하는 Service 까지 있다?!! 의존성이 매우 복잡해지고 심할 경우 순환 의존성이 생기는 것을 경험했습니다. 그래서 가급적 Service 계층을 얇게 유지하려고 하는 것 같아요! 🙂



결론적으로

비즈니스와 관련된 예외 또는 Service 계층에서만 처리할 수 있는 예외 -> Service 처리
특정 SQL에 대하여 단순히 데이터가 없거나 있음을 나타내는 예외 -> Repository 처리
를 선호하고 있습니다!



답변이 도움이 되었으면 좋겠습니다. 감사합니다!! 🙇🙇