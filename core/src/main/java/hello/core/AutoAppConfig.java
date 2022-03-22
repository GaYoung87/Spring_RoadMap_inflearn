package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  // 들어가보면 @Component 붙어있음
@ComponentScan(  // @Component가 붙은 클래스를 찾아 자동으로 등록해주는데, 그 중에서 제외할 것
        // 시작점 찾기 -> hello.core.member하면 그 밑에있는 애들 다 탐색!
        // 만약, 작성하지 않는다면? 시작점은 hello.core
        // 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것
        basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class,  // 이 클래스에서도 찾음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
// @Configuration은 수동으로 등록해주는 것이므로 제외시켜야함
)
// 보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만,
// 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.
public class AutoAppConfig {
    // 기존의 AppConfig와 다르게 @Bean으로 설정한 클래스가 하나도 없음!

    // 만약. 여기에도 Bean 이름붙여 설정하고, MemoryMemberRepository에도 @Component를 붙인다면?
    // 1. MemoryMemberRepository는 memoryMemberRepository로 됨(무조건 첫글자 소문자로 변경됨)
    // 2. 이때 돌리면 성공
    // 3. overriding되기 때문 -> 수동 빈 등록이 우선권 있음음
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
