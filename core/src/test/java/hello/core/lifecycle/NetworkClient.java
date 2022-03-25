package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // [빈생명주기 콜백시작] - 방법1. 인터페이스 implements InitializingBean, DisposableBean
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.start");
//        connect();
//        call("초기화 연결 메시지");
//        // 스프링의존관계 끝나고 호출해줌
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    // [빈생명주기 콜백시작] - 방법2. 빈 등록 초기화, 소멸메서드
//    public void init(){
//        System.out.println("NetworkClient.init");
//        connect();
//        call("초기화 연결 메시지");
//        // 스프링의존관계 끝나고 호출해줌
//    }
//
//    public void close() {
//        System.out.println("NetworkClient.close");
//        disconnect();
//    }

    // [빈생명주기 콜백시작] - 방법3. 어노테이션: @PostConstruct, @PreDestroy (이거 사용해라!!!)
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
        // 스프링의존관계 끝나고 호출해줌
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
