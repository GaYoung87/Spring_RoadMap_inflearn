package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

   // 1. 기존 _ 생성자 주입
//    public MemberSerivce memberSerivce() {
//        return new MemberServiceImpl(new MemoryMemberRepository());  // 생성자 주입
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

    // AppConfig 리팩터링
    // why? 중복존재 + 역할에 따른 구현 잘 안보임
    public MemberSerivce memberSerivce() {
        return new MemberServiceImpl(memberRepository());  // 생성자 주입
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    // 장점1. 역할과 구현 클래스가 한 눈에 드러남
    // 장점2. 만약 나중에 DB가 설정되거나 다른 구현체로 변경이 필요할 때 private부분만 변경하면 됨


}
