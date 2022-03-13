package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberSerivce;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")  // 우리가 만들었던 순수한 DI컨테이너의 문제점 확인하기 위함
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1.조회: 호출할 때마다 객체 생성
        MemberSerivce memberSerivce1 = appConfig.memberService();

        // 2.조회: 호출할 때마다 객체 생성
        MemberSerivce memberSerivce2 = appConfig.memberService();

        // 참조 값이 다른 것을 확인
        System.out.println("memberSerivce1 = " + memberSerivce1);
        System.out.println("memberSerivce2 = " + memberSerivce2);
        // 다른 객체 가지고옴 -> 새로운 객체 생성 -> 이때 4개만들어짐
//        memberSerivce1 = hello.core.member.MemberServiceImpl@71e9ddb4
//        memberSerivce2 = hello.core.member.MemberServiceImpl@394df057
        Assertions.assertThat(memberSerivce1).isNotSameAs(memberSerivce2);  // 성공
    }

    // 해당 객체가 딱 하나만 생성되고 공유하도록 설계해야함 => 싱글톤

    @Test
    @DisplayName("싱글톤 패턴을 이용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 자바뜰 때 생성한거를 가져다 사용 -> 동일
        // singletonService1 = hello.core.singleton.SingletonService@5fbe4146
        // singletonService2 = hello.core.singleton.SingletonService@5fbe4146

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // instance비교 : same, 수치비교 : equal
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1.조회: 호출할 때마다 객체 생성
        MemberSerivce memberSerivce1 = ac.getBean("memberService", MemberSerivce.class);

        // 2.조회: 호출할 때마다 객체 생성
        MemberSerivce memberSerivce2 = ac.getBean("memberService", MemberSerivce.class);

        // 참조 값이 다른 것을 확인
        Assertions.assertThat(memberSerivce1).isSameAs(memberSerivce2);  // 성공
    }

}