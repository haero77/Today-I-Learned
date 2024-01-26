## 발단 

```java
Resolved [org.springframework.http.converter.HttpMessageNotReadableException:
JSON parse error: Cannot construct instance of `jpabook.jpashop.api.
MemberApi$UpdateMemberRequest` (although at least one Creator exists): cannot
deserialize from Object value (no delegate- or property-based Creator);
```

`@RequestBody` 를 이용해서 Request DTO를 생성할 때  

## 원인

```java
// MemberApi.UpdateMemberRequest(Request DTO로 사용된 이너 클래스) 
@Data
@AllArgsConstructor
private static class UpdateMemberRequest {
    private String name;
}
```

### 그럼 왜 Reflection은 기본 생성자가 필요한가

## 해결

```java
// MemberApi.UpdateMemberRequest(Request DTO로 사용된 이너 클래스) 
@Data
@NoArgsConstructor // Reflection을 위한 기본 생성자 추가
@AllArgsConstructor
private static class UpdateMemberRequest {
    private String name;
}
```




### 결론 


---

※ References

- [[우아한테크코스] 기본 생성자가 필요한 이유 (Why the default constructor is needed) (feat. Jackson ObjectMapper + Reflection)
  ](https://da-nyee.github.io/posts/woowacourse-why-the-default-constructor-is-needed/)