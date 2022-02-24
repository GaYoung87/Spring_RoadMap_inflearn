package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);  // Optional은 findById의 결과가 null인 경우 Optional로 감싸서 null반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
