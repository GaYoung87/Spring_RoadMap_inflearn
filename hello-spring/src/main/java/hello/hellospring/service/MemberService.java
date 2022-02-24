package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 이름 중복 회원 X

        /**
        // memberRepository.findByName(member.getName());에 ctrl+alt+v하면 바뀜
        Optional<Member> result = memberRepository.findByName(member.getName());
        // result.get();  // 이런식으로 바로 꺼내도 괜찮은데 이왕이면 사용X
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        });  // Optional로 감싸서 가능!
         */

        // Optional이 이쁘지 않기때문에 한번에 작성하면 보기좋음음
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    // Shift+Ctrl+Alt+T(리팩토링 관련) -> extend method
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                 .ifPresent(m -> {
                     throw new IllegalStateException("이미 존재하는 회원입니다!");
                 });
    }

    // 전체회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
