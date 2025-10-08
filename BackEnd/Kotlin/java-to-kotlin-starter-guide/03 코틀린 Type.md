<!-- TOC -->
* [기본 타입 캐스팅](#기본-타입-캐스팅)
* [일반 타입 캐스팅](#일반-타입-캐스팅)
* [](#)
* [코틀린 특이한 타입: Any, Unit, Nothing](#코틀린-특이한-타입-any-unit-nothing)
  * [Any](#any)
  * [Unit](#unit)
  * [Nothing](#nothing)
* [String Interpolation / String Indexing](#string-interpolation--string-indexing-)
  * [String Interpolation](#string-interpolation)
  * [String Indexing](#string-indexing)
<!-- TOC -->

> - 코틀린의 변수는 초기값을 보고 타입을 추론하며, 기본 타입들간의 변환은명시적으로이루어진다.
> - 코틀린에서는 is, !is, as, as? 를 이용해 타입을 확인하고 캐스팅한다.
> - 코틀린의 Any는 Java의 Object와 같은 최상위타입이다.
> - 코틀린의 Unit은 Java의 void와동일하다.
> - 코틀린에 있는 Nothing은 정상적으로 끝나지 않는 함수의 반환을
  의미한다.
> - 문자열을 가공할 때 ${변수}와 ””” ”””를 사용하면 깔끔한 코딩이 가능하다.
> - 문자열에서 문자를 가져올때의 Java의 배열처럼[]를 사용한다

# 기본 타입 캐스팅

- 자바에서는 암시적 캐스팅 가능

```java
int num1 = 10;
long num2 = num1; // 암시적 캐스팅
```

- 코틀린에서는 명시적 캐스팅 필요(`to변환타입()` 사용)

```kt
val num1: Int = 10
val num2: Long = num1.toLong() // 명시적 캐스팅
```

# 일반 타입 캐스팅

- 자바에서는 instanceof 사용

```java
public static void printIfPerson(Object obj) {
    if (obj instanceof Person) {
        Person person = (Person) obj; // 명시적 캐스팅
        System.out.println(person.getName());
    }
}
```

- 코틀린에서는 is 사용

```kt
fun printIfPerson(obj: Any) {
    if (obj is Person) {
        // 스마트 캐스팅: 별도의 명시적 캐스팅 없이도 컴파일러가 obj를 Person 타입으로 인식 
        println(obj.name)
    }
}


fun printIfNotPerson(obj: Any) {
    if (obj !is Person) {
        println("Not a person")
    }
}
```

- as 를 사용한 캐스팅
  - 그냥 as: 명시적 캐스팅, value가 Type이 아니면 예외 발생
  - as?: 안전한 캐스팅, value가 Type이 아니면 null 반환
    - value가 null인 경우에도 null 반환

```kt
fun printIfPerson(obj: Any?) {
    val person = obj as? Person // person null 가능
    println(person?.name)
}
```


#  

# 코틀린 특이한 타입: Any, Unit, Nothing

## Any

- Java의 Object 역할.(모든 객체의 최상위타입)
- 모든 Primitive Type의 최상의 타입도 Any이다.
  - 코틀린에서는 Primitive, Reference 타입 구분 없이 모두 대문자 사용 (예: Int, Double, Boolean)
- Any 자체로는 null을 포함 할 수 없어 null을 포함하고 싶다면
  Any?로표현.
- Any 에equals / hashCode / toString 존재.

## Unit

- Java의 void 역할.
- void와 달리 Unit은 그 자체로 타입 인자로 사용 가능
  - Java에서는 Void 클래스 따로 사용.

## Nothing

- 함수가 정상적으로 종료되지 않았다는 것을 표현하는 타입.
- 무조건 예외를 던지는 함수, 무한루프 함수 등
- 코딩하면서는 거의 안 씀.

```kt
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```

# String Interpolation / String Indexing 

## String Interpolation

Java

```java
Person person = new Person(name: "최태헌", age: 100);
String log = String.format("사람의 이름은 %s이고 나이는 %s세 입니다", person.getName(), person.getAge());

StringBuilder builder = new StringBuilder();
builder.append("사람의 이름은");
builder.append(person.getName());
builder.append("이고 나이는");
builder.append(person.getAge());
builder.append("세 입니다");
```

Kotlin: ${} 사용하여 문자열 보간 가능.

```kt
val person = Person(name = "최태헌", age = 100)
val log = "사람의 이름은 ${person.name}이고 나이는 ${person.age}세 입니다"

val name = "haero77"

val format = """
    abc
    efg
    ${name}
    """.trimIndent()
```

- 로컬 변수의 경우 중괄호 생략 가능.
  - 변수 이름만 사용하더라도, ${변수} 사용이 좋음
    - 가독성
    - 일괄변환
    - 정규식 활용 등.

## String Indexing

```kt
val str = "ABCD"
println(str[0])
```

