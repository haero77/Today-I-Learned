package hello.aop.exam;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 5번에 1번 실패하는 요청
     * 이렇게 간헐적으로 실패할 경우 재시도하는 AOP가 있으면 편리하다.
     */
    public String save(String itemId) {
        seq++;

        if (seq % 5 == 0) {
            throw new IllegalArgumentException("예외 발생");
        }

        return "ok";
    }

}
