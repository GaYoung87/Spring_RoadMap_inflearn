package hello.core.member;

// MemberSerivce의 구현체
public class MemberServiceImpl implements MemberSerivce{
    // MemberServiceImpl은 인터페이스인 MemberRepository, 구현체인 MemoryMemberRepository 둘다 의존
    // DIP 위반!

    // 인터페이스(MemberRepository)는 작성 -> 이거만 가지고있으면 nullpointException 발생
    // 구현객체(MemoryMemberRepository) 넣어주기
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // ctrl+shift+enter하면 ;포함해서 나옴

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
