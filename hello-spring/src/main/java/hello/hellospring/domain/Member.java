package hello.hellospring.domain;

import javax.persistence.*;

@Entity  // jpa가 관리하는 entity
public class Member {

    // GenerationType.IDENTITY : 회원 추가 시 id를 직접 넣는 것이아니라 DB가 알아서 넣어줌
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")  // DB의 컬럼명 중 name과 mapping됨 -> 이거 컬럼명이랑 안맞춰주면 에러남
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
