package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void PrototypeBeanFind() {
        // 여기에 SingletonBean 넣어주면, 자동으로 컴포넌트스캔이 되어 등록됨
        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 출력한 이유: PrototypeBean을 조회하기 직전에 생성됨
        System.out.println("find prototypeBean1");
        // 이때 새로운 객체가 생성됨
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // close시키려면 수동으로 해줘야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        // close했지만, close되지 않음 -> 프로토타입이라 만들고 버린것임(스프링컨테이너 기준)
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
