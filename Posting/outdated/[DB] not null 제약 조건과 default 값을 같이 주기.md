> ~~ 을 다룹니다.

# 들어가며

평화롭게 회사에서 개발 중, 한 동료분께서 다음과 같은 얘기를 해주셨습니다. 

> _선호님, not null 제약 조건과 default 제약 조건을 같이 설정하면 좋아요._


예를 들어 다음과 같이 회원 테이블을 만들기 위한 DDL이 있다고 해봅시다. 

```sql
create table members
(
    member_id bigint       not null auto_increment,
    grade     varchar(255) not null,
    primary key (member_id)
);
```

회원 등급을 관리하기 위해 `grade`라는 컬럼을 사용한다고 합니다. <br>


```sql
create table members
(
    ...
    grade     varchar(255) not null default 'BRONZE',
    ..
);
```

그분과의 얘기를 통해, 더 공부해볼 몇 가지 키워드를 찾았습니다.

- 검색 시 null 인 것과 아닌 것의 차이

# 

`not null`과 `default`를 같이 설정하면 무엇이 좋은지  

## not null 제약 조건과 default 제약 조건

### not null, default 모두 설정되어 있을 때

```sql
create table members
(
    member_id bigint       not null auto_increment,
    grade     varchar(255) not null default 'BRONZE',
    primary key (member_id)
);
```

### not null만 설정되어 있을 때

```sql
create table members
(
    member_id bigint       not null auto_increment,
    grade     varchar(255) not null,
    primary key (member_id)
);
```

### default만 설정되어 있을 때

```sql
create table members
(
    member_id bigint       not null auto_increment,
    grade     varchar(255) default 'BRONZE',
    primary key (member_id)
);
```
 

# 애플리케이션 레벨에서 잘 막으면 되지 않을까?

그럼 이제 근본적으로 의문이 듭니다. 

> _`not null`이 꼭 필요한가? 애플리케이션 레벨에서 잘 막으면 되지 않을까?_

예를 들어, 생성자에 가드(=인스턴스가 유효한 값만 가지도록 인스턴스 생성 시점에 유효성 검사를 하는 것)를 설치하여   

# 한 걸음 더: null 과 not null 컬럼의 차이

# 마치며


## Reference

- https://www.baeldung.com/hibernate-notnull-vs-nullable



