package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")  // 22. 조회 빈 2개이상일 때 - @Qualifier
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    // ctrl+shift+t -> create new test
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
