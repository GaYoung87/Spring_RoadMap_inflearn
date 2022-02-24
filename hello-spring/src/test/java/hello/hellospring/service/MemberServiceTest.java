package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    // 지속적으로 다른 레포지토리 객체가 생성이 되면
    // 다른 인스턴스라 내용이 달라질 수 있지만,
    // memberService에서의 memoryMemberRepository랑 memberServiceTest가 서로 다른 인스턴스(레포지토리)
    // -> 지금은 static이라 문제가 없지만, 만약 static이 없으면 문제가 생김
    // -> 문제 = 다른 DB사용 -> memberService의 final MemberRepository 로 두고 constructor만들기(동작 전에 외부에서 넣어주는거로 바꾸기)
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // 동작 전에 외부에서 넣어주는거로 바꾸기
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // test는 예외 flow 훨씬중요함
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("sring");  // 여기도 spring이면 회원가입에서 터진다 -> test해보기 -> clear해줘야함

        // when
        Long saveId = memberService.join(member);

        // then
        // repository에 있는 값이 맞아?를 찾기 위해서는 repository 꺼내야함
        Member findMember = memberService.findOne(saveId).get();  // 값 바로가지고옴
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예약() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // 좋은 예외처리는 아님!!
        // 중복되면 validateDuplicateMember에 걸려서 예외가 터짐!
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다!");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // NullPointerException이면 test실패

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다!");


        // then

    }
}