package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    // url 이동하기위해 버튼누르는건 getMapping(주로 조회할때)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // postMapping은 주로 데이터를 폼에 넣어 전달할 때 사용
    @PostMapping("/members/new")
    public String create(MemberForm form) {  // MemberForm의 name에 setName을 통해 gayoung이라는 데이터 들어감
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";  // home화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // model에서 attributeName의 members가 th:each="member : ${members}"의 members임
        return "members/memberList";
    }
}
