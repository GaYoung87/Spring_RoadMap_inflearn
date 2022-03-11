package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        // 1. 직접 객체 생성
//        MemberSerivce memberSerivce = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null,null);
//
//        Long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
//        memberSerivce.join(member);
//
//        Order order = orderService.createOrder(memberId, "itmeA", 10000);
//
//        System.out.println("order = " + order);
//        System.out.println("order.calculatePrice() = " + order.calculatePrice());

        // 2. AppConfig 사용해 객체 생성
//        AppConfig appConfig = new AppConfig();
//        MemberSerivce memberSerivce = appConfig.memberSerivce();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberSerivce memberSerivce = applicationContext.getBean("memberSerivce", MemberSerivce.class);  // 이름 + 반환타입
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Order order = orderService.createOrder(memberId, "itmeA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());

    }
}
