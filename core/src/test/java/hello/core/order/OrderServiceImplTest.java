package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    // 순수한 자바코드로 test
    @Test
    void createOrder() {
        // 1. 수정자 주입
        // setsetMemberRepository, setDiscountPolicy로 주입하면 에러남!
        // why? 값이 세팅되어있지 않아서

        // 2. 생성자 주입
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        // 만약 new MemoryMemberRepository, new FixDiscountPolicy쓰면 Member값이 없어서 에러남!
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itmeA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
