package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.beanfind.ApplicationContextExtendsFindTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // 지역변수 공유안됨!!
        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // 문제 -> 지역변수 공유됨
        // ThreadA: A사용자 주문 금액 조회
        // 다른스레드에서 들어가도 동일한 객체이기 때문에 10000->20000로 바뀜
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);  // 20000 why? A사용자가 가격을 정하기전에 B사용자가 들어왔기 때문

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
