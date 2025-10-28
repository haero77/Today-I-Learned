<!-- TOC -->
* [1. setter에 관한 이야기](#1-setter에-관한-이야기)
  * [개인적으로 setter를 열어는 두지만 사용하지 않는 방법을 선호](#개인적으로-setter를-열어는-두지만-사용하지-않는-방법을-선호)
<!-- TOC -->

# 1. setter에 관한 이야기

```kotlin
@Entity
class Book(
    val name: String,
) {
}
```

- setter 대신 좋은 이름의 함수를 사용하는 것이 훨씬 clean
- 그런데 name에 대한 setter는 public이기 때문에 사용할 수도 있다.

```kotlin
// 방법 1: backing property 사용
@Entity
class Book(
    private var _name: String,
) {
    val name: String
        get() = this._name
}
```
```kotlin
// 방법2: custom setter 사용
@Entity
class Book(
    name: String,
) {
    var name: String = name
        private set
}
```

👉 두 방법 모두 프로퍼티가 많아지면 번거롭다

## 개인적으로 setter를 열어는 두지만 사용하지 않는 방법을 선호

- 다행이 현재 팀에서도 setter를 사용하면 안된다는 사실을 모든 개발자 분들이 체득하고 있다.
- 트레이드 오프의 영역이고, 팀 컨벤션을 잘 맞추면되지 않을까 생각.

# 2. 생성자 안의 프로퍼티, 클래스 body 안의 프로퍼티

```kotlin
// 생성자 안의 프로퍼티
@Entity
class User(
    var name: String,

    val age: Int? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = ArrayList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
}

// body 안의 프로퍼티
@Entity
class User(
  var name: String,

  val age: Int? = null,
) {
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = ArrayList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
```

- 꼭 primary constructor에 프로퍼티를 선언해야할까?

1. 모든 프로퍼티를 생성자에 넣거나,
2. 프로퍼티를 생성자 혹은 클래스 body안에 구분해서 넣을 때 명확한 기준이 있거나

둘 중에 무엇을 사용하든, 명확한 기준이 있고 그것을 기준으로 사용해야 읽기 좋은 코드가 된다고 생각. **중요한 건 명확한 기준**


# 3. JPA와 data class

- Entity에서는 data class를 사용하지 않는 것이 좋다
- equals, hashCode, toString 모두 JPA Entity와는 100% 어울리지 않는 메소드!
  - User, UserLoanHistory 양방향 매핑하면 equals에서 무한 루프 발생

# 4. (TIP) Entity가 생성되는 로직을 찾고 싶다면 constructor 지시어를 명시적으로 작성하고 추적하자!