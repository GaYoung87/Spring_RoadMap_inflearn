package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    // build.gradle에서 data-jpa를 등록함
    // 스프링부트가 자동으로 EntityManager 만들어줌

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // 저장, 조회, 업데이트는 쿼리 짤 필요 없음
    @Override
    public Member save(Member member) {
        em.persist(member);  // persist=영구저장-> setId등 모든것을 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // 리스트로 탐색 시
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체를 대상으로 쿼리를 날림 -> entity 자체를 select하는 것임
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
    }
}
