<!-- TOC -->
* [length()를 사용한 null 컬럼과 empty string인 컬럼 필터링](#length를-사용한-null-컬럼과-empty-string인-컬럼-필터링)
  * [length()를 사용할 때 컬럼이 null이라면 어떻게 되는가?](#length를-사용할-때-컬럼이-null이라면-어떻게-되는가)
    * [GPT왈](#gpt왈-)
* [문제: length function은 index를 탈 것인가?](#문제-length-function은-index를-탈-것인가)
  * [GPT 왈](#gpt-왈)
<!-- TOC -->

# length()를 사용한 null 컬럼과 empty string인 컬럼 필터링

```sql
select *
from
    Domain domain0_ 
where
    length(domain0_.emailAddress)<>0
```

- 위 `length(domain0_.emailAddress)<>0`를 사용해서 나온 쿼리 결과를 보면,
  - emailAddress 가 null 이 아니고, empty string이 아닌 Domain 엔티티의 리스트가 리턴된다.

## length()를 사용할 때 컬럼이 null이라면 어떻게 되는가?

- 결과 역시 null 이 나온다.
- null 과 <> 0을 비교한다면? 
  - `length(domain.emailAddress) <> 0`의 결과 역시 null로 표시된다.

### GPT왈 

> If domain0_.emailAddress is NULL, the length() function in most SQL database systems would also 
> return NULL. In SQL, comparisons with NULL typically result in a boolean UNKNOWN rather than TRUE or FALSE.
> 
> In the context of your query, if domain0_.emailAddress is NULL, the condition length(domain0_.
> emailAddress) <> 0 would evaluate to UNKNOWN. Consequently, the row would not be included in the result set because the condition is not explicitly TRUE.
> 
> In summary:
> 
> If domain0_.emailAddress is not NULL, the condition would evaluate based on the length of the 
> email address.
> If domain0_.emailAddress is NULL, the condition would evaluate to UNKNOWN, and the row would 
> not be included in the result set.

<br>

# 문제: length function은 index를 탈 것인가?

## GPT 왈

> 일반적으로 SQL 데이터베이스에서 'length()' 함수는 각 행의 문자열 길이를 계산하는 작업으로 인해 직접적으로 인덱스를 활용하지 않습니다. 
> 이는 효율적으로 인덱싱할 수 없는 작업이기 때문입니다.
> 
> 그러나 'emailAddress' 열에 인덱스가 있는 경우 쿼리의 다른 작업이 해당 인덱스를 효율적으로 활용할 수 있는 경우 (예: 비교 또는 정렬),
> 데이터베이스 옵티마이저는 쿼리 실행을 최적화하기 위해 여전히 해당 인덱스를 사용할 수 있습니다. 
> 
> 단, 이는 'length()' 함수를 특별히 고려한 것이 아닙니다.
> 
> 예를 들어, 'emailAddress' 열의 길이를 기준으로 행을 선택하는 쿼리가 있고 'WHERE emailAddress IS NOT NULL'과 같은 조건도 있는 경우,
> 데이터베이스는 'emailAddress'의 인덱스를 'NULL' 값을 효율적으로 필터링하는 데 사용할 수 있습니다.
> 
> 요약하면, 'length()' 함수 자체는 인덱스를 직접적으로 사용하지 않지만, 해당 열에 인덱스가 있는 경우 다른 조건이나 작업과 함께 결합되어 전체 쿼리 성능에 이점을 
> 줄 수 있습니다.
