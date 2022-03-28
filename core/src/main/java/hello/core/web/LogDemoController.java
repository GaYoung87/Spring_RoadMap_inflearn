package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger;
    // MyLogger: 얘를 내놓으려하는데 request가 없음
    // 생존범위: 고객요청(http request)이 들어와서 나갈 때까지
    // 근데 지금 스프링 띄우는과정에서는 http request가 안들어옴
    // 이 빈은 실제 고객이 요청이 와야 실행가능! -> 스프링빈에게 요청하는 단계를 의존관계 주입단계가 아니라 실제 고객 요청이 왔을 때로 지연시켜야함 -> Provider쓰기!!!

    // 해결책
    private final ObjectProvider<MyLogger> myLoggerProvider;
    // - myLogge를 찾을 수 있는 디펜던시 룩업할 수 있는 애(DL)가 뜬다
    // - 원하는 주입 시점에 주입할 수 있음

    // proxy 설정했다면
    private final MyLogger myLogger;


    @RequestMapping("log-demo")
    @ResponseBody  // 화면 없이 문자 바로 리턴 -> 문자 그대로 응답보냄
    public String logDemo(HttpServletRequest request) {// HttpServletRequest: 고객 요청정보 받을 수 있음
        // MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURI().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        // myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$ef899579 -> 얘가 Provider처럼 동작하는 것
        // 진짜 myLogger가 아니라 껍데기만 가져다놓은것
        // 의존관계 주입도 가짜 프록시 객체 주입됨
        // ac.getBean("myLogger", MyLogger.class)를 조회해도 프록시 객체가 조회됨

        // 기능을 호출하는 시점에 진짜 로직을 찾아 동작
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");  // 이거도 가짜 프록시 객체의 메서드를 호출한 것
        return "OK";
    }

    // 과정
    // 1. MyLogger myLogger = myLoggerProvider.getObject();에서 처음만들어짐
    // 2. 그때, init()이 호출되면서 uuid를 request랑 연결
    // 3. setrequestURL 담아놓고 로그찍기
    // 4. 로그찍는 순간에는 이미 uuid, requestURL있기 때문에 에러없이 찍힘
    // ** 수많은 요청이 와도 요청마다 따로 객체 관리 **
}
