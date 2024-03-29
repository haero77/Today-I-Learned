# List 와 Set 의 차이 

- List 인터페이스
  - 데이터간 순서 유지
  - 데이터 중복 허용
- Set 인터페이스
  - 데이터간 순서 유지되지 않음
  - 데이터 중복을 허용하지 않음
  
### 🆀 해시 테이블을 이용하여 Set이 구현된 이유 
- Key가 중복되지 않고 데이터 순서가 랜덤이라는 해시테이블이 특징이 Set의 특징과 일치
- 해시테이블에 데이터가 얼마나 많든간에 키를 검색할 때의 시간복잡도는 `O(1)`

### List 와 Set의 차이를 물어보는 이유

- 기본 중의 기본이라, 대답 못하면 기본기가 부족한 사람으로 보인다. 
- 혹시 Set이 어떻게 구현되는지 아시나요?
  - 깊게 파고드는 사람인지  
- set은 보통 해시테이블로 구현되기 때문에 해시테이블과 관련한 추가 질문
- 언제 set을 쓰면 좋은지 왜 그런지 -> 해시 테이블 개념과 엮기위함 
  - set은 데이터 중복을 허용하지 않고 데이터의 순서도 보장하지 않는 특징을 갖는데요. 이러한 set이 해시테이블로 구현되는 이유는 해시 테이블이 set과 같은 특징을 갖고 있기 때문입니다. 해시 테이블은 키가 중복 될 수 없고 순차적으로 저장되는 것이 아닌 랜덤하게 저장됩니다. 해시 테이블이 이런 특징은 set의 특징과 일치하기 때문에 set을 구현할 때는 hash table의 키에 데이터를 저장하는 형태로 구현을 하게 됩니다. 해시테이블은 키를 검색할 때의 시간 복잡도가 빅오원입니다. 즉 얼마나 많은 데이터들을 해시테이블에 저장하고 있는지와 상관없이 늘 빠르게 키를 검색할 수 있기 때문에 데이터 검색을 리스트에서 하는 것보다는 해시 테이블로 구현된 set에서 하는 것이 시간적으로 훨씬 유리합니다. 
- https://www.youtube.com/watch?v=CMgpTGs_N_w