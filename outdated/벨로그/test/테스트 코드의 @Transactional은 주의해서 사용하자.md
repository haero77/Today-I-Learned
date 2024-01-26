> 🎯 GOAL
> - 테스트 코드에서 `@Transactional` 사용할 때 생길 수 있는 부작용을 이해한다.

# 들어가며

최근 [테스트 코드 강의](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C)를 들으며 공부를 하던 중, 강사님께서 TC 종료 후 DB를 초기화하기 위해 **`tearDown` 메서드와 `@Transactional` 중 무엇을 사용해야할까**라는 내용에 대해 언급해주셨다. 충분히 고민해 볼만한 내용이라고 생각되어, 본 포스팅에서는 테스트 코드에서 **`@Transactional`을 사용할 때 생길 수 있는 부작용**에 대해서 살펴보고, **둘 중 어느 것을 사용해야 할지** 의견을 제시한다.

<br>

# 그냥 `@Transactional`을 사용하면 되지 않을까?

`tearDown` 메서드와 `@Transactional` 중 무엇을 사용할까 고민하기 이전에, 이런 생각이 들 수 있다.

**_"그냥 테스트 코드에 `@Transactional`을 사용하면 되는거 아닌가?"_**

맞다. 사용해도 된다. 프로덕션 코드에 `@Transactional`을 사용하는 것과는 달리, 테스트 코드에서 `@Transactional`을 사용하면 TC가 끝나고 자동으로 데이터가 롤백되니 귀찮게 `tearDown` 메서드를 작성할 필요도 없다. 그런데, 그 **부작용을 인지하지 않은 상태에서 그냥 사용하게 되면 비즈니스 로직이 실패하는 등의 문제를 야기할 수 있다.**

간단한 예제를 통해, **테스트 코드의 `@Transactional` 사용 시 부작용**이 대체 무엇인지 알아보자.

<br>

## @Transactional이 가져오는 착각: 문제 없는 프로덕션 코드

회원 도메인에서, 회원 객체의 필드를 수정하는 예제를 가져왔다.

```java
// 도메인
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	public Member(String username) {
		this.username = username;
	}

	public void changeUsername(String username) {
		this.username = username;
	}

}
```

- 회원은 `id`와 `username`을 필드로 갖는다.
- `changeUsername`을 통해 `username` 필드를 수정한다.

```java
// 비즈니스 로직
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	public void changeUsername(Long id, String newUsername) {
		Member findMember = memberRepository.findById(id).orElseThrow();
		findMember.changeUsername(newUsername);
	}

}
```

- 서비스 레이어에서는 `영속성 컨텍스트`의 `변경 감지`를 통해 회원의 `username`을 수정한다.

눈치가 빠른 독자라면, `MemberService::changeUsername` 에 `@Transactional`이 없는 것을 알아차렸을 수도 있겠다. 맞다. 이 부분이 이어 설명할 **테스트 코드에서의 `@Transactional` 사용 시 부작용**과 관련있다.

이제, 테스트 코드에 `@Transactional` 을 사용했을 때랑 `tearDown()` 을 사용했을 때를 비교해보자.

<br>

### `@Transactional`을 사용한 경우

```java
// 테스트 코드
@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@DisplayName("회원의 username을 변경할 수 있다.")
	@Test
	void changeUsername() {
		// given
		Member member = new Member("oldUsername");
		Member savedMember = memberRepository.save(member);

		Long savedMemberId = savedMember.getId();
		String newUsername = "newUsername";

		// when
		memberService.changeUsername(savedMemberId, newUsername);

		// then
		Member findMember = memberRepository.findById(savedMemberId).get();
		assertThat(findMember.getUsername()).isEqualTo(newUsername);
	}

}
```

간단하게 `MemberServic::changeUsername` 을 검증하는 TC를 작성하고, 실행했다.

![image](https://github.com/haero77/Today-I-Learned/assets/65555299/a7af352f-812b-4237-ae9e-582d28acfdc8)

**비즈니스 로직에 트랜잭션이 없음에도 불구하고, 테스트는 통과**한다. 테스트 코드의 `@Transactional`로 인해 테스트 시작 전 트랜잭션이 시작하여 테스트 메서드가 트랜잭 범위 안에 들어가고, 이로 인해 `변경 감지`가 정상적으로 동작했기 때문이다.

문제는, 실제 비즈니스 로직에는 트랜잭션 처리가 이루어지지 않았다는 점이다. 즉, **테스트 코드에서의 `@Transactional`을 사용하면, 비즈니스 로직에 트랜잭션이 있는 것처럼 착각하게 만든다.** TC가 내 프로덕션 코드가 정상적으로 실행될 것을 보장해줘야하는데, 그러질 못하고 있는 것이다. 이렇게 문제없는 코드라고 착각해서 배포라도 하는 경우, 운영 상황에서 해당 로직 수행이 실패할 것은 자명하다. 그렇기에, **테스트 코드에서 `@Transactional`을 사용할 때는 주의를 해야한다.**

<br>

### `tearDown` 메서드를 사용한 경우

```java
@SpringBootTest
class MemberServiceTest {

        // ... 

        @AfterEach
        void tearDown() {
           memberRepository.deleteAll();
        }
	
        // TC
	
}
```

같은 TC를 이번에는 `@Transactional` 말고 `tearDown()`을 통해 DB를 초기화하도록 작성했다.

<br>

<img width="1395" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/2a59400f-5174-487d-99cf-1d5d8ed2a75a">

테스트가 실패한다. 트랜잭션이 없으니 `변경 감지`가 작동하지 않은 것이다. 이제 이 테스트가 성공하도록 프로덕션 코드를 수정하자.

<br>

```java
// 비즈니스 로직
@RequiredArgsConstructor
@Service
public class MemberService {

        // ...
	
        @Transactional
        public void changeUsername(Long id, String newUsername) {
            // ...
        }

}
```

문제가 되었던 로직에 트랜잭션 처리를 해주었다. 테스트를 다시 수행하면 실패했던 테스트가 성공한다.

테스트 코드에 `@Transactional`을 사용할 때와는 달리, `tearDown()`을 사용하니 프로덕션 코드에 있었던 문제를 TC가 찾아냈다. 이제 적어도 해당 로직이 트랜잭션 처리와 관련해서는 정상적으로 동작할 것임을 보장할 수 있다.

<br>

# 부작용을 인지하고 사용하자

그럼 이제 생각해봐야한다.

**_"테스트 코드의 `@Transactional`이 프로덕션 코드가 문제가 없는 것처럼 착각하게 만드니까, 무조건적으로 `tearDown()` 사용이 나은건가?"_**

이 문제에 대한 답은 **_"트레이드 오프를 고려해서 선택하라"_** 이다. `tearDown()`이 만능은 아니다. 엔티티 간에 연관 관계가 복잡하게 얽혀있는 경우는 어떤 repository를 먼저 `clear` 해야할지 신경써야 할 수도 있고, 결국 이것도 비용이기 때문이다.

이에 대해 강사님은 _**"팀원들 모두가 이러한 부작용을 충분히 인지하고 있다면 그 때 도입을 고려해보면 좋다"**_ 라고 의견을 주셨다. 필자도 이에 동의한다. 편의를 위해 테스트 코드에서는 `@Transactional` 사용하는 것을 컨벤션으로도 정할 수도 있고, 부작용을 미연에 방지하기 위해 `tearDown()`을 사용할 수도 있다. 결국은 **정하기 나름**이라고 생각한다. 기술 선택보다도 **팀원들간에 공유를 통해 일관성있는 코드를 작성하는 것이 핵심**이니까 말이다.

필자가 선호하는 방식은 우선 `tearDown()`을 활용해보면서 **불편함을 일부러 체험**하고, 정 불편할 때는 `@Transactional`을 사용하도록 리팩토링해나갈 생각이다. 처음부터 편해버리면 얼마나 편한지 가늠할 수 없지 않은가?

<br>

# 마치며

이번 포스팅에서는

- 테스트 코드에서 `@Transactional`을 사용하면 프로덕션 코드에 문제가 없다고 착각할 수 있음

을 사례를 통해 알아보고, `tearDown()`을 사용할 때와 비교하여 그 선택 기준에 대해서도 살펴봤다.

근래들어 부쩍 느끼는 것은, 선택에 정답은 없으며 트레이드 오프를 고려하여 기술을 선택할 수 있는 개발자로 성장하는 것이 중요하다는 것이다. 그런 개발자로 성장할 수 있기를 바라며, 본 포스팅은 여기서 마친다.

_마침_

<br>

### 더 공부해볼 것

- `deleteAll()` Vs. `deleteAllInBatch()`

<br>

### Reference

- [Practical Testing: 실용적인 테스트 가이드 - 인프런](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C)