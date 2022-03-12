package hello.core.xml;

import hello.core.member.MemberSerivce;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class xmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberSerivce memberSerivce = ac.getBean("memberSerivce", MemberSerivce.class);
        Assertions.assertThat(memberSerivce).isInstanceOf(MemberSerivce.class);
    }
}
