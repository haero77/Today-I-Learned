### 질문

ShortUrlViewCount : ShortUrl 을 N : 1 관계로 설정했습니다. 조회수를 확인할 때 7일간의 조회수를 보여주어야하므로 URL 한 개당 조회수 튜플이 7개 이상이 생기게 됩니다. 혹시 더 효율적으로 스키마를 구성하는 방법이 있을까요?

### 답변

URL 당 조회수를 저장할 때, 각 날짜마다의 조회수를 별도의 테이블에 저장하는 대신, 다음과 같이 하나의 테이블에 모든 조회수를 저장하는 것이 더 효율적입니다.

1. ShortUrl 테이블: URL에 대한 기본 정보를 저장하는 테이블
    - id (PK)
    - url

2. ShortUrlViewCount 테이블: URL에 대한 조회수 정보를 저장하는 테이블
    - id (PK)
    - short_url_id (FK)
    - date
    - view_count

위와 같은 테이블 구성을 통해 조회수를 저장하는 테이블은 ShortUrlViewCount 테이블 하나만 있으므로, 조회수 정보를 저장할 때마다 새로운 테이블을 만들 필요가 없습니다. 이를 통해 불필요한 중복 데이터를 제거할 수 있습니다.

또한, 이러한 방식으로 조회수 정보를 저장하면, 날짜 별 조회수를 조회할 때도 간단한 SQL 질의문으로 조회할 수 있습니다. 예를 들어, 7일간의 조회수를 조회하는 SQL 질의문은 다음과 같이 작성할 수 있습니다.

```sql
SELECT date, SUM(view_count) AS total_view_count
FROM ShortUrlViewCount
WHERE short_url_id = {short_url_id}
AND date >= DATEADD(day, -6, GETDATE())
GROUP BY date
ORDER BY date ASC;
```

이렇게 하면, URL 한 개당 조회수 튜플이 7개 이상이 생기는 문제를 해결할 수 있으며, 조회수 정보를 저장하는 방식도 더 효율적으로 만들 수 있습니다.


## 질문

URL 의 조회수와 Referer 를 기록하고 싶다.
테이블 설계 해줘

## 답변

URL의 조회수와 Referer를 기록하기 위해 다음과 같은 테이블 구조를 사용할 수 있습니다.

1. ShortUrl 테이블: URL에 대한 기본 정보를 저장하는 테이블
    - id (PK)
    - url

2. Referer 테이블: Referer 정보를 저장하는 테이블
    - id (PK)
    - referer

3. ShortUrlView 테이블: URL의 조회수와 Referer 정보를 저장하는 테이블
    - id (PK)
    - short_url_id (FK)
    - referer_id (FK)
    - view_time

위와 같은 테이블 구조를 사용하여 URL의 조회수와 Referer 정보를 기록할 수 있습니다.

- ShortUrl 테이블: URL에 대한 기본 정보를 저장하는 테이블입니다. 각 URL은 고유한 ID를 가지고 있습니다.

- Referer 테이블: Referer 정보를 저장하는 테이블입니다. 각 Referer는 고유한 ID를 가지고 있습니다.

- ShortUrlView 테이블: URL의 조회수와 Referer 정보를 저장하는 테이블입니다. 각 조회는 해당 URL에 대한 Referer와 조회 시간을 기록하며, 각 조회는 고유한 ID를 가지고 있습니다.

이렇게 하면 URL의 조회수와 Referer 정보를 각각 저장하고, 두 정보를 연결하여 조회할 수 있습니다. 또한, 조회 시간 정보를 함께 저장하여 언제 조회가 일어났는지도 파악할 수 있습니다.