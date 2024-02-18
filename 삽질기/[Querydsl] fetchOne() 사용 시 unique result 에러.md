# 상황

> NonUniqueResultException: query did not return a unique result: 2

- exists를 querydsl로 구현하고 싶어 fetchOne()을 썼을 때 위 같은 에러가 발생했다.

# 원인 

- fetch 결과가 하나로 특정되지 않기 때문에 발생

# 해결 

- 이렇게 exists를 대체할 목적으로는 fetchOne() 대신 fetchFirst()를 사용해서 해결했다.
  - 애초에 fetchOne() 자체가 unique 하다는 전제를 깔고 있으므로, fetchOne()을 쓰는 상황 자체가 얼마나 많을지는 미지수.

