package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface받기 위해서는 extends + interface는 다중상속 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {  // id식별자=Long

    // JpaRepository 가지고있으면 SpringDataJpaMemberRepository 구현체를 자동으로 만들어줌 -> 구현체 자동 등록

    // JpaRepository: crud, findAll, save 등등 가지고있음 => 공통프로그램이다

    @Override
    Optional<Member> findByName(String name);  // 인터페이스 이름만으로 개발끝남
}
