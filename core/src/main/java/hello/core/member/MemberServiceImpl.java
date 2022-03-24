package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 이거 붙이면 @Bean 등록안해도 됨 -> 자동으로 MemberServiceImpl등록
// 그러면 주입은 어케하할꺼야? @Autowired를 통해 자동 의존관계 주입
public class MemberServiceImpl implements MemberService {  // MemberSerivce의 구현체
    // MemberServiceImpl은 인터페이스인 MemberRepository, 구현체인 MemoryMemberRepository 둘다 의존
    // DIP 위반!

    // 인터페이스(MemberRepository)는 작성 -> 이거만 가지고있으면 nullpointException 발생
    // 구현객체(MemoryMemberRepository) 넣어주기
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 이렇게 작성하는 건 추상+구현이므로 배우가 무대구성하는거까지 포함된 것.
    // 우리가 해야하는 것 : 배우가 연기만 하는 것

    // 이렇게하면 MemberServiceImpl에 MemoryMemberRepository에 대한 이야기 없음!
    // 따라서 추상화에만 의존함 -> DIP지킴
    private final MemberRepository memberRepository;
    // ctrl+shift+enter하면 ;포함해서 나옴

    @Autowired  // 자동 의존관계주입 -> ac.getBean(MemberRepository.class)역할을 함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
