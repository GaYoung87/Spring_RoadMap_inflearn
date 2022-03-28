package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        // 1. 문제점 방법으로 하면 isEqualTo(2)가 되어야함
        // 3. 해결책 방법으로 코드를 작성했기 때문에 isEqualTo(1)이 됨
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        Assertions.assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {
        // 1. 문제점: 생성 시점에 주입되므로 PrototypeBean 고정됨
//        private final PrototypeBean prototypeBean;
//
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            int count = prototypeBean.getCount();
//            return count;
//        }

        // 2.내가원하는 것 - 프로토타입 새로 만들어지게하기! -> @Autowired는 좋은 코드아님,,
//        @Autowired
//        ApplicationContext applicationContext;
//
//        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            int count = prototypeBean.getCount();
//            return count;
//        }

        // 3. 해결책
        @Autowired
        // 스프링 컨테이너에 조회할 때, 대신 조회해주는 것
        // 스프링이 자동으로 주입해줌
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic() {
            // getObject()하면 그때서야 컨테이너에서 프로토타이핑을 찾아주는 기능만 제공
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            int count = prototypeBean.getCount();
            return count;
        }

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PrototypeBean.destroy");
    }
}
