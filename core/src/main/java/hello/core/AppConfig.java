package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 설정정보
// 만약, @Configuration이 없으면, 싱글톤X -> 계속 new MemoryMemberRepository()를 호출하는 것과 같음
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
//  ----------------------------------------------------------------------
    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    /** [내 생각]
     * call AppConfig.memberService    // memberService 호출
     * call AppConfig.memberRepository  // memberService에서 memberRepository를 호출하면서 발생
     * call AppConfig.memberRepository  // memberRepository 호출
     * call AppConfig.orderService
     * call AppConfig.memberRepository  // orderService에서 memberRepository호출
     *
     * [실제] -> 싱글톤 보장
     * call AppConfig.memberService
     * call AppConfig.memberRepository
     * call AppConfig.orderService
     * */
    @Bean  // @Bean을 통해 스프링컨테이너에 등록됨
    public MemberSerivce memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());  // 생성자 주입
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    // 장점1. 역할과 구현 클래스가 한 눈에 드러남
    // 장점2. 만약 나중에 DB가 설정되거나 다른 구현체로 변경이 필요할 때 memberRepository, DiscountPolicy부분만 변경하면 됨

    // @Bean 사용시에도 싱글톤으로 변경하면됨 -> 그치만 스프링컨테이너가 다 해준다~
    // 있는객체 재활용 -> 성능 좋아짐
}
