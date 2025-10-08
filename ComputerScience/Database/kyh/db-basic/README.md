> [김영한의 실전 데이터베이스 - 기본편](https://inf.run/XRUC3) 강의 정리 레포.

# 주요 비즈니스 규칙 및 제약사항


> 우리가 만들 쇼핑몰의 비즈니스 규칙은 다음과 같다.

> **_왜 이런 비즈니스 규칙과 제약사항을 먼저 살펴볼까?_**
>
> 실제로 데이터베이스를 만들 때는 먼저 어떤 데이터가 필요하고, 그 데이터들이 어떻게 연결되는지 설계하
는 과정이 꼭 필요하다.
하지만 이번 강의는 데이터베이스 설계가 주제가 아니기 때문에, 이런 규칙과 구조가 있다는 것만 간단히 이
해하고 넘어가자.
데이터베이스 설계에 관한 부분은 데이터베이스 설계 강의에서 자세히 다룬다.

1. **고객 가입**: 모든 고객은 고유한 이메일 주소를 가져야 한다. 이름과 이메일은 필수 정보다.
2. **주문 생성**: 주문은 반드시 특정 고객(`user_id` )과 특정 상품(`product_id` )에 연결되어야 한다.
    1. 하나의 주문에 한 종류의 상품만 선택할 수 있다. 상품의 수량은 선택할 수 있다.
3. **주문 상태 관리**: 주문이 생성되면 기본 상태는 'PENDING'이며, 이후 'COMPLETED', 'SHIPPED',
   'CANCELLED'로 변경될 수 있다.
    1. PENDING(대기)
    2. SHIPPED(배송)
    3. COMPLETED(완료)
    4. CANCELLED(취소)
4. **재고 관리**: 주문이 발생하면 해당 `products` 테이블의 `stock_quantity` (재고)는 주문 `quantity` (수량)만
   큼 차감되어야 한다.
   이 로직은 데이터베이스가 아니라 애플리케이션에서 구현해야 한다.
5. **직원 관리 구조**: 직원은 매니저를 가질 수 있으며, 매니저 또한 직원이다. 매니저가 없는 최상위 직원이 존재할 수
   있다.

# ERD

```mermaid
erDiagram
    users ||..o{ orders : ""
    products ||..o{ orders : ""
    employees ||..o{ employees : "manage"
    
    users {
        bigint user_id PK "고객 고유 식별자 [auto_increment]" 
        varchar(255) name "고객명 [not null]"
        varchar(255) email UK "고객 이메일 [not null]"
        varchar(255) address "고객 주소"
        date birth_date "고객 생년월일"
        datetime created_at "고객 정보 생성 일시 [default current_timestamp]"
    }
    
    orders {
        bigint order_id PK "주문 고유 식별자 [auto_increment]"
        bigint user_id FK "주문한 고객의 ID [not null]"
        bigint product_id FK "주문된 상품의 ID [not null]"
        datetime order_date "주문 생성일시 [default current_timestamp]"
        int quantity "주문 수량 [not null]"
        varchar(50) status "주문 상태 [default 'PENDING']"
    }
    
    products {
        bigint product_id PK "상품의 고유 식별자 [auto_increment]"
        varchar(255) name "상품명 [not null]"
        varchar(100) category "카테고리"
        int price "가격 [not null]"
        int stock_quantity "재고 수량 [not null]"
    }
    
    employees {
        bigint employee_id PK "직원 고유 식별자 [auto_increment]"
        varchar(255) name "직원 이름 [not null]"
        bigint manager_id FK "해당 직원의 관리자 ID
            [employees 테이블의 employee_id 를 참조. 
            최상위 관리자는 NULL 값을 가질 수 있음.]"
    }
    
    sizes {
        varchar(10) size PK "상품의 사이즈 옵션
            (e.g., 'S', 'M', 'L', 'XL')"
    }
    
    colors {
        varchar(20) color PK "상품의 색상 옵션
            (e.g., 'Red', 'Blue', 'Black')"
    }

```