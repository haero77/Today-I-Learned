# JUnit5 시작하기 - 기본 어노테이션

# 0. 시작하기

## Gradle Dependecy

```java
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.0'
    testImplementation 'org.assertj:assertj-core:3.23.1'
}
```

<br>

# 1. 기본 어노테이션

## @Test

```java
@Test
void create1() {
    Study study = new Study();
    assertNotNull(study);
    System.out.println("create1()");
}
```

- 테스트를 실행할 메서드
- JUnit4 에서는 `public`으로 선언된 메서드만 테스트 가능했으나, JUnit5 부터는 `public` 없이 테스트 가능하다.

## @BeforeAll, @AfterAll

```java
@BeforeAll
static void beforeAll() {
    System.out.println("@BeforeAll");
}

@AfterAll
static void afterAll() {
    System.out.println("@AfterAll");
}
```

- 각각 모든 테스트가 시작되기 전 딱 한 번, 모든 테스트가 끝나고 나서 딱 한 번 실행된다.
- `private` 은 사용 불가능, `default` 는 사용 가능하다.
- 리턴 타입이 있으면 안 된다.
- 일반적으로 `static void` 메서드로 작성한다.

## @BeforeEach, @AfterEach

```java
@BeforeEach
void beforeEach() {
    System.out.println("@BeforeEach");
}

@AfterEach
void afterEach() {
    System.out.println("@AfterEach");
}
```

- 모든 테스트마다, 각각 테스트를 실행하기 직전 / 테스트를 실행한 직후에 실행된다.
- `static` 메서드일 필요는 없다.

## @Disabled

```java
@Test
@Disabled // 아직 어떻게 고쳐야할 지 모르는 경우 등에 사용.
void create2() {
    System.out.println("create2()");
}
```

![img.png](../images/img.png)

- 테스트를 비활성화하는 어노테이션
- 아직 어떻게 코드를 고쳐야하는지 모르는 경우에 사용한다. (깨지는 테스트 등)
- 깨지는 테스트는 고치는 것이 좋다. 부득이한 경우에 사용하자.