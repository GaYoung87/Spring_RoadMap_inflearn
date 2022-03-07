package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // 스프링에서 dataSource만들어줌!
    // resources/application.properties에서 설정을 했기 때문
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        // @Configuration 읽고 현재 로직을 호출해서 스프링 빈에 등록
        return new MemberService(memberRepository());
        // memberService는 -> 스프링빈에 등록되어있는 memberRepository()를 엮어줘야 함
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
