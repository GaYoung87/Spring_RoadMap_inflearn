package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        // @Configuration 읽고 현재 로직을 호출해서 스프링 빈에 등록
        return new MemberService(memberRepository());
        // memberService는 -> 스프링빈에 등록되어있는 memberRepository()를 엮어줘야 함
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
