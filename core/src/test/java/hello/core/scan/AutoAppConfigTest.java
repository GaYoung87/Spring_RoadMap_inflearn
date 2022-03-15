package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.member.MemberSerivce;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);  // AppConfig.class도 스프링컨테이너에 등록됨

        MemberSerivce memberSerivce = ac.getBean(MemberSerivce.class);
        Assertions.assertThat(memberSerivce).isInstanceOf(MemberSerivce.class);
    }
}
