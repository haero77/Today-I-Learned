package org.festilog.springtransaction.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final LogMessageRepository logMessageRepository;

    /**
     * 회원과 DB로그를 함께 남기는 비즈니스 로직
     * 현재 별도의 트랜잭션은 설정하지 않음
     */
    public void joinV1(final String username) {
        final Member member = new Member(username);
        final LogMessage logMessage = new LogMessage(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(member);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logMessageRepository 호출 시작 ==");
        logMessageRepository.save(logMessage);
        log.info("== logMessageRepository 호출 종료 ==");
    }

    /**
     * joinV1과 같은 기능을 수행.
     * DB 로그 저장 시 예외가 발생하면 예외를 복구한다.
     * 현재 별도의 트랜잭션은 설정하지 않는다.
     */
    public void joinV2(final String username) {
        final Member member = new Member(username);
        final LogMessage logMessage = new LogMessage(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(member);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logMessageRepository 호출 시작 ==");
        try {
            logMessageRepository.save(logMessage);
        } catch (RuntimeException e) {
            /**
             * 예외를 안 잡으면 어떻게 될까?
             * 👉 고객한테 예외 화면이 보여버릴 것.
             *
             * 이에 비해 예외를 잡아서 정상흐름으로 돌리는 것의 장점
             * 👉 로그 저장 안 된 것 정도는 나중에 잡아서 복구해버리면 되지. 여기서 중요한 것은 회원을 저장하는 것이니까.
             *    겨우 로그 남기는 것 때문에 고객이 불편할 필요는 없다는 관점.
             */
            log.info("logMessage 저장에 실패했습니다. logMessage={}", logMessage.getMessage());
            log.info("정상 흐름 반환");
        }
        log.info("== logMessageRepository 호출 종료 ==");
    }
}
