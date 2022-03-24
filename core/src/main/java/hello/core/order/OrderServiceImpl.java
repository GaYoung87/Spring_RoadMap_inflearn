package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor  // final붙은거를 파라미터로 받는 생성자 만듦
                          // 만약 private final Object object;가 추가되었다고하면 매우 편함 -> private만 추가하면 끝
public class OrderServiceImpl implements OrderService{
    // 할인정책을 변경하기 위해서는 할인의 클라이언트인 OrderServiceImpl을 수정해야함
    /** 이때, 문제발생!!!
     * 1. 역할과 구현 분리 ok
     * 2. 다형성도 활용하고 인터페이스와 구현 객체 분리 ok
     * 3. OCP, DIP와 같은 객체지향 설계원칙 준수 no!
     *     why? 주문서비스 클라이언트(OrderServiceImpl)는 추상(인터페이스)인 DiscountPolicy뿐만 아니라,
     *          구현클래스인 FixDiscountPolicy, RateDiscountPolicy에도 의존함
     *   -> OCP(변경하지 않고 확장 가능) -> 지금 코드는 기능을 확장해서 변경하면 클라이언트 코드에 영향 줌!!
    */

    // 회원과 할인정책 찾아야하므로 필요
    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 이렇게 작성하면 OrderServiceImpld이 DiscountPolicy에도 의존하지만, FixDiscountPolicy에도 의존함 -> DIP위반!
    // DIP = 구체화가 아닌 추상화에 의존해라
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;  // 인터페이스(추상)에만 의존 + 이거만 적으면 구현에 할당된게없어서 nullPointException

    // 1.생성자주입_구현체에 대해 알 수 없음
    /**
     * 해결책 : 누군가가 클라이언트인 OrderServiceImp에 DiscountPolicy의 구현객체를 대신 생성하고 주입해줘야함
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 11. 조회 빈 2개이상일 때 - @Autowired
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

    // 22. 조회 빈 2개이상일 때 - @Qualifier
//    @Autowired
//    // @Qualifier("mainDiscountPolicy")를 @MainDiscountPolicy로 바꿔도 됨 -> @MainDiscountPolicy어노테이션 만들었으니까!
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy")DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 33. 조회 빈 2개이상일 때 - @Primary


    // 2. 수정자주입 : private final에서 final 없애야함
//    @Autowired(required = false)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setMemberRepository(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 3. 필드주입
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 4. 일반 메서드 주입
    // 이때 final을 붙이면 init자체가 에러
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 설계가 잘 된것!
        // why? OrderService입장에서는 할인에 대해서는 discountPolicy가 알아서하고 결과만 알려줘!
        //      만약 할인에 대한 변경이 필요하면 주문까지 손안대도 됨
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
