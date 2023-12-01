package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /**
     * 회원 도메인 설계의 문제점
     * 이 코드의 설계상 문제점은 무엇일까요?
     * 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요?
     * DIP를 잘 지키고 있을까요?
     * 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
     * 주문까지 만들고나서 문제점과 해결 방안을 설명
     */

    // 인터페이스가 아니라 구현체에 의존하고 있다.
    // -> 다른 저장소로 변경할 때 코드의 변경이 생긴다. (OCP 위반)
    // -> 구체화에 의존하고 있음(DIP: '추상화에 의존해야지, 구체화에 의존하면 안 된다.')
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
