package hello.core.discount;

import hello.core.member.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixedAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.isGradeVip()) {
            return discountFixedAmount;
        }
        return 0;
    }

}
