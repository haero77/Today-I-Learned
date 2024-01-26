# Test Driven Development

> 💡 TDD
>
> _**"프로덕션 코드보다 테스트 코드를 먼저 작성하여 테스트가 구현 과정을 주도하도록 하는 방법론."**_

<img width="611" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/4c59476b-1403-415f-9f90-624ea4218c9d">

## TDD는 어떻게 하는가

### RED-GREEN Refactoring

<img width="826" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/0162954b-086a-4b82-97c3-4283fb82507c">

1. `RED`
   - 실패하는 테스트를 먼저 작성
2. `GREEN`
   - 테스트 통과(초록불)를 보기 위해 최소한의 코드 작성
3. `REFACTORING`
   - 테스트 통과를 유지하면서 리팩토링

<br>

## TDD의 핵심

- TDD 핵심가치는 `피드백`이다.
- 내가 작성하는 코드에 대해 자주, 빠르게 피드백 받을 수 있다.

<br>

### `선 기능 구현, 후 테스트 작성`의 문제점

- 테스트 자체의 누락 가능성
- 특정 테스트 케이스(예: 해피 케이스)만 검증할 가능성
  - 기능을 구현할 때는 해피 케이스에 사고가 갇혀서 구현할 가능성 높음
- 잘못된 구현을 다소 늦게 발견할 가능성

<br>

### `선 테스트 작성, 후 기능 구현`하면 뭐가 좋은가? 

- 복잡도가 낮은(=유연하며 유지보수가 쉬운), 테스트 가능한 코드로 구현할 수 있게 한다.
  - 예) _"`LocalDateTime`을 파라미터로 분리해야겠다."_ 라는 생각을 떠올릴 수 있다.
- 쉽게 발견하기 어려운 엣지(edge) 케이스를 놓치지 않게 해준다.
- 구현에 대한 빠른 피드백을 받을 수 있다.
- 과감한 리팩토링이 가능해진다.

<br>

### TDD는 관점의 변화

- `클라이언트` 관점에서의 `피드백`을 주는 Test Driven

<br>

# 키워드 정리

- TDD
- RED-GREEN-REFACTORING

<br>

### 추가적으로 찾아볼 것

- 애자일(Agile) 방법론 Vs. 폭포수 방법론
  - [애자일 성명서](http://agilemanifesto.org/) (Kent Beck, Robert C. Martin, Martin Fowler 등)
- 익스트림 프로그래밍(XP, eXtreme Programming)
- 스크럼(Scrum), 칸반(kanban)