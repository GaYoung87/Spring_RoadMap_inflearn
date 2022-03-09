package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int disocuntFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {  // enum은 ==쓰는 것이 맞음
            return disocuntFixAmount;
        } else {
            return 0;
        }
    }
}
