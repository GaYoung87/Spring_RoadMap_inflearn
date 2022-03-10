package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        // 1. 직접 객체 생성
//        MemberSerivce memberSerivce = new MemberServiceImpl();
//
//        // new Member(1L, "memberA", Grade.VIP); 여기에서 ctrl+alt+v하면
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        memberSerivce.join(member);
//
//        Member findMember = memberSerivce.findMember(1L);
//        System.out.println("new Member = " + member.getName());  // soutv단축키
//        System.out.println("find Member = " + findMember.getName());

        // 2. AppConfig를 통해 객체 생성
        AppConfig appConfig = new AppConfig();
        MemberSerivce memberSerivce = appConfig.memberSerivce();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Member findMember = memberSerivce.findMember(1L);
        System.out.println("new Member = " + member.getName());  // soutv단축키
        System.out.println("find Member = " + findMember.getName());
    }
}
