package hello.hellospring.controller;

public class MemberForm {

    // createMemberForm.html의 id="name"과 매칭이 되면서 값이 들어옴
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
