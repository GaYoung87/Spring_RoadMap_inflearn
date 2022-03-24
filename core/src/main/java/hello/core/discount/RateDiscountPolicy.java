package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("mainDiscountPolicy")  // 22. 조회 빈 2개이상일 때 - @Qualifier
                                     // 에러발생시 어디가 이상한지 알수없고, 에러발생가능성 높음
@MainDiscountPolicy
@Primary
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
