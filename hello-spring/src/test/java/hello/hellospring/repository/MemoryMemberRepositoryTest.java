package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("gayoung");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 검증: 내가 입력한 member랑 저장한 후 id로 찾은 result랑 동일?
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, result);  // 출력되는건 없고 녹색불
        Assertions.assertThat(member).isEqualTo(result);  // core
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("gayoung1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("gayoung2");
        repository.save(member2);

        Member result = repository.findByName("gayoung1").get();  // .get으로 꺼내기
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("gayoung1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("gayoung2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
