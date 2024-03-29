  
> 🎯포스팅 목표  
> - 빌더 패턴에 대해 이해한다.  
> - lombok 의 @Builder 가 적용된 클래스를 컴파일 시 어떤 바이트 코드가 생성될 지 예측할 수 있다.   
> - @Builder 사용 시 주의점에 대해 이해한다.  
  
# 들어가며  
  
요즘 개발을 하며 객체를 생성할 때는 주로 빌더와 정적 팩토리 메서드를 통해 하고 있습니다. 사실 얼마전까지만 해도 주로 생성자를 이용하여 객체를 생성했었는데, [우빈님과의 질답](https://www.inflearn.com/questions/913002)과 _이펙티브 자바_ (`아이템 1. 생성자 대신 정적 팩터리 메서드를 고려하라`, `아이템 2. 생성자에 매개변수가 많다면 빌더를 고려하라`)를 통해 빌더와 정적 팩토리 메서드의 유용함을 느껴 최근에는 해당 방식을 많이 사용하는 편입니다.  

실제로 `정적 팩토리 메서드`를 사용해보니, *객체 생성 시 의미 있는 이름을 사용* 한다는 점이 좋았습니다. 예를 들어 `회원` 객체를 생성할 때 초기값으로 특정한 값을 가져야하는 것이 비즈니스 정책이라면, `defaultOf()` 처럼 네이밍하는 식입니다. 

또, `빌더`를 사용해보니 생성자가 갖던 한계인 *'메서드 시그니처가 같으면 오버로딩이 불가능하다'* 라는 문제를 어느 정도 해결할 수 있었습니다. `점층적 생성자 패턴(telescoping constructor pattern)` 을 쓰지 않아도 되니 코드 역시 깔끔하게 유지할 수 있었고요. 특히 생성자를 사용할 때, 파라미터 개수가 많아짐으로써 생기는 파라미터가 몇 개인지, 어떤 의미인지 일일이 확인해야하는 문제가 사라지므로 개발자의 실수 여지를 줄일 수 있다는 점이 가장 큰 장점이었습니다.

그런데 문제는 빌더를 사용하기 위해 큰 고민 없이 롬복의 `@Builder`를 사용하다보니, `@Builder` 와 관련하여 의문점이 생길때마다 스스로 명확히 답을 내리기가 어려웠습니다. 예를 들면,
  
- ***`@Builder` 는 클래스에 붙이나, 생성자에 붙이나 차이가 없는건가?***
	- 차이가 없으면 왜 다른 개발자분들(예: 호돌맨님)은 왜 생성자에 `@Builder` 를 사용하시는 걸까?
- ***`@Builder` 사용 시 생성자의 접근 제어자에 따른 사용 방법 차이는 없는건가?***
  
같은 의문점들입니다. 그래서 이번 포스팅에서는 위같은 의문점들에 답을 하기 위해, `@Builder` 사용 시 실제로 `@Builder` 가 적용된 Java 소스를 컴파일 할 때 생기는 `.class` 파일을 뜯어보면서 `@Builder`의 동작 원리를 살펴보겠습니다.



# 본문  
  
## 빌더 패턴  
  
- 빌더 패턴에 대한 간단한 설명 
- 빌더 패턴 예제
- 
먼저 빌더 패턴이 무엇인지에 대해
  
## @Builder  

### 이게 

그게 맞나 싶지만



  
  
# 마치며   
  
### 참고  
  
- [[Java-27] 자바 생성자에 대한 고찰](https://catch-me-java.tistory.com/40)