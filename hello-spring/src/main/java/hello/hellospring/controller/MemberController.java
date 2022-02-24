package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// @Controller는 Spring이 관리하는 것이므로 스프링빈에 올라가고 @Autowired로 연결해줘야함!
public class MemberController {
    // Spring실행하면 Spring Container라는 통이 생김
    // -> @Controller있으면 MemberController객체를 생성해서 spring안에 넣어두고 관리
    // -> 스프링 빈이 관리된다

    private final MemberService memberService;
    // new로 생성해서 쓸 수도 있지만, spring container에 등록해서 container에서 받아서 쓸 수 있게 해야함
    // why? new하면 다른 컨트롤러들이 MemberService사용할텐데 여러개 생성할 필요없이 하나 생성해서 공유하면 됨
    // container 하나 등록하면 여러가지 효과 볼 수 있음 -> 나중에 설명

    // @Autowired붙이면 컨테이너 생성할 때 생성자 호출 -> memberService를 연결시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
