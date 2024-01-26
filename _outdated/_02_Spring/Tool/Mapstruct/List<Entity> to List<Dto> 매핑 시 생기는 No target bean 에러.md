# 상황

- List to List Mapper 를 구현하고 빌드를 하면 빌드가 안 되는 경우가 있다.

```java
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);
    
    List<MemberDto> toMemberDto(List<Member> members);
            
}
```

- 위의 경우 다음과 같은 오류를 출력한다.

```java
error: No target bean properties found: can't map Collection element 
"Member member" to "MemberDto memberDto". Consider to declare/implement a mapping method: "MemberDto map(Member value)".
```

- List to List 로 변환하기 전에 우선 Entity to Dto 변환 메서드가 있어야 한다는 뜻이다.

# 해결

- Entity to Dto 메서드를 추가해준다.

```java
@Mapper
public interface MemberMapper {
    
    // ...

    MemberDto toMemberDto(Member member);
    
    List<MemberDto> toMemberDto(List<Member> members); // 리스트 매핑 이전에 Entity to Dto 또는 Dto to Entity 매핑 메서드가 필요하다.
            
}
```
