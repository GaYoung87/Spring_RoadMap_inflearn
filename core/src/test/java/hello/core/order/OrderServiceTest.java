package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberSerivce memberSerivce = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;  // long을 쓰면 null이 들어갈 수 없음
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Order order = orderService.createOrder(memberId, "itmeA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
