package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고료
 */
public class MemberRepository {

    // [ 변수 지정 ]
    // static을 사용했기 때문에, MemberRepository가 아무리 new로 계속 생성되어도
    // store, sequence는 하나만 생성됨
    // 아래에서 싱글톤 보장했기 때문에 static 없어도 괜찮긴함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // [ 싱글톤으로 만들기 ]
    private static final MemberRepository instance = new MemberRepository();

    // 무조건 getInstance()로 조회해서 MemberRepository 찾아야함
    public static MemberRepository getInstance() {
        return instance;
    }
    // 싱글톤 만들 때는 private으로 일단 생성자 생성해서 아무나 생성하지 못하게 막아줘야함
    private MemberRepository() {
    }

    // [ 메서드 ]
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // ArrayList에 값을 넣거나, 밖에서 조작해도 store 자체를 보호하고 싶어서 한 방법
        // store에 있는 member를 직접 가지고와서 값을 변경하면 변경 됨
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();  // test에서 사용용
   }

}
