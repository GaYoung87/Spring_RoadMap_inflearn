package hello.core.singleton;

// 객체를 미리 생성해두는 가장 단순하고 안전한 방법
public class SingletonService {

    // 자기 자신을 내부의 private static으로 설정하면 클래스 레벨에 올라가서 딱 하나만 만들어짐
    private static final SingletonService instance = new SingletonService();

    /**
     * 이렇게 하면 자바가 뜰 때 바로 singletonService의 static 영역의
     * 오른쪽에 있는 new SingletonService()를 내부적으로 실행해서
     * 객체(자기자신)을 생성해서 인스턴스에 참조 넣어둠
     */
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        // 외부에서 new키워드로 객체 인스턴스가 생성되는 것을 막음
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
