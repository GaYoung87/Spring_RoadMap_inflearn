package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ConfigurableApplicationContext는 AnnotationConfigApplicationContext의 상위 인터페이스
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();  // 닫아줘야함
    }

    @Configuration
    static class LifeCycleConfig {
        // [빈생명주기 콜백시작] - 방법1. 인터페이스 implements InitializingBean, DisposableBean
//        @Bean
//        public NetworkClient networkClient() {
//            NetworkClient networkClient = new NetworkClient();  // 아직 url정보 안들어옴
//            networkClient.setUrl("http://naver.dev");  // 연결안된 페이지 입력하면 (ex.http://hello-spring.dev) Bad address: listen에러 뜬다
//            return networkClient;
//        }

        // [빈생명주기 콜백시작] - 방법2. 빈 등록 초기화, 소멸메서드
//        @Bean(initMethod = "init", destroyMethod = "close")
//        public NetworkClient networkClient() {
//            NetworkClient networkClient = new NetworkClient();  // 아직 url정보 안들어옴
//            networkClient.setUrl("http://naver.dev");  // 연결안된 페이지 입력하면 (ex.http://hello-spring.dev) Bad address: listen에러 뜬다
//            return networkClient;
//        }

        // [빈생명주기 콜백시작] - 방법3. 어노테이션: @PostConstruct, @PreDestroy (이거 사용해라!!!)
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();  // 아직 url정보 안들어옴
            networkClient.setUrl("http://naver.dev");  // 연결안된 페이지 입력하면 (ex.http://hello-spring.dev) Bad address: listen에러 뜬다
            return networkClient;
        }
    }
    // 스프링빈의 라이프사이클
    // 객체생성 -> 의존관계 주입
    // 생성자 주입의 경우는 예외!! -> 객체만들 때 이미 스프링빈이 파라미터에 같이 들어가야함

    // 스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비완료됨.
    // 따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.
    // 초기화란? 객체가 외부랑 진짜 연결해야할 때(처음 제대로 일 시작할 때)
    // 의존관계 주입이 완료되면, 스프링빈에게 콜백 메서드를 통해 초기화시점 알려줌

    // 최대한 생성자에서 다 해버리면 되는거 아닌가?(networkClient()에서 다 하면 안되나?) 파라미터 넘기고, 다 생성자 주입 시키면 안되는것?
    // -> 객체 생성과 초기화 분리필요!
    // 객체생성시에는 값 세팅정도만! 초기화는 외부연결과같은 무거운 일들!
}
