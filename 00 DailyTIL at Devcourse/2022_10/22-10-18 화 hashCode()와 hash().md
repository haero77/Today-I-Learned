### TIL
- LMS 강의 
- Objects.hashCode() 와 Objects.hash()

### 공유하고 싶은 내용

- `논리적으로 같은 객체`(예: 인스턴스 필드의 값이 같은 경우)를 식별하기 위해, `Object` 클래스의 `equals()` 메서드를 오버라이딩하였습니다.
이어 논리적으로 동일한 객체는 `hashCode`의 결과가 같아야하므로 `Object.hashCode()` 또한 재정의 하게 되었는데요, 그러면서
`Objcets.hashCode()`와 `Objects.hash()`의 차이가 궁금해졌습니다.

- 알게된 점
  - **인자로 하나의 객체만 전달하면,`Objects.hash()`와 `Objects.hashCode()`는 서로 다른 결과를 리턴합니다**
  - **인자가 두 개 이상일 때는, `Objects.hash()`를 이용합니다.**
```java
@DisplayName("hash와 hashCode의 리턴값이 다른지 테스트")
@Test
void test2() {
    String firstOne = "one";
    String secondOne = "one";

    int hashCode1 = Objects.hash(firstOne);
    int hashCode2 = Objects.hashCode(secondOne);

    assertThat(hashCode1).isNotEqualTo(hashCode2);
}
```

- 참고: [Objects.hash() vs Objects.hashCode()](https://www.baeldung.com/java-objects-hash-vs-objects-hashcode)
- 이펙티브 자바: `ITEM 11. equals를 재정의하려거든 hashCode도 재정의하라`

### TMI

- 내일부터는 달리기를 해보겠습니다.. 오래 앉아 있기가 어렵네요 😂
