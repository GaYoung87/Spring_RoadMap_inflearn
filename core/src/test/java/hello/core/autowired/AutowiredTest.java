package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        // true면 예외터짐! (찾을 수 없으므로)
        // false면 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 -> 에러안터짐! 그냥 안나옴
        public void setNoBean1(Member member) {  // 스프링컨테이너 관리되지 않음
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {  // 값이 있으면 있는대로, 없으면 Optional.empty로 나옴
            // @Nullable, Optional은 스프링 전반에 걸쳐서 지원됨
            // 생성자 자동 주입에서 특정 필드에만 사용해도 된다.
            System.out.println("member = " + member);
        }
    }
}
