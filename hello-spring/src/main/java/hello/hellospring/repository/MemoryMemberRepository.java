package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 동시성 문제 있을 수 있지만 여기서는 간단하게만 진행
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null인경우 대비+클라이언트에서 뭔가를 할 수 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프돌면서 확인하다가 동일한게 있으면 반환, 없으면 optional에 null포함시켜 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  // store.values()=members
    }

    public void clearStore() {
        store.clear();
    }
}
