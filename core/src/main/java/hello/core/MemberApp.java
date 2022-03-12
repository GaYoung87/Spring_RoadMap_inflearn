package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // 1. 직접 객체 생성
//        MemberSerivce memberSerivce = new MemberServiceImpl();
//
//        // new Member(1L, "memberA", Grade.VIP); 여기에서 ctrl+alt+v하면
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        memberSerivce.join(member);
//
//        Member findMember = memberSerivce.findMember(1L);
//        System.out.println("new Member = " + member.getName());  // soutv단축키
//        System.out.println("find Member = " + findMember.getName());

        // 2. AppConfig를 통해 객체 생성
//        AppConfig appConfig = new AppConfig();
//        MemberSerivce memberSerivce = appConfig.memberSerivce();

        // 스프링 생성
        // AppConfig에 있는 환경설정정보를 가지고 스프링 컨테이너에 객체생성한걸 관리 + AppConfig의 @Configuration이용
        // AnnotationConfigApplicationContext는 ApplicationContext 인터페이스의 구현체
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberSerivce memberSerivce = applicationContext.getBean("memberService", MemberSerivce.class);  // 이름 + 반환타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Member findMember = memberSerivce.findMember(1L);
        System.out.println("new Member = " + member.getName());  // soutv단축키
        System.out.println("find Member = " + findMember.getName());
    }
}
