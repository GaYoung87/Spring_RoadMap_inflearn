package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 저장소 생성
    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 동시성 issue때문에 ConcurrentHashMap 클래스 사용하는게 좋음
    // 다양한 곳에서 이 곳에 들어올 때 new로 HashMap을 생성하기 때문에 동시성 이슈 발생

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
