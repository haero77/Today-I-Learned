### 테스트 할 수 없는 LocalDateTime.now()

### 방법 1. LocalDateTime.now() 를 외부에서 주입한다.

### 방법 2. LocalDateTime.now() 만 모킹하면 되지 않을까?

## 제어할 수 없는 것에 의존하지 말자

- 테스트 때문에 엔티티를 변경하는 
- 캡슐화를 위해서 자체적으로는 
- 비즈니스 로직이 
- 제어할 수 없다면 
  - 엣지 케이스로 바운더리 테스트가 가능한지
  - 모킹으로 해결가능한 부분인지?
- 테스트 자체를 하지 말아야 하는 부분인가?


---

※ Reference

- https://www.youtube.com/live/DJCmvzhFVOI?feature=share