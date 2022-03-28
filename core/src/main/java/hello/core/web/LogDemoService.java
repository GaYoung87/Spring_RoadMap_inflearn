package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final MyLogger myLogger;

    // 해결책
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    // - myLogge를 찾을 수 있는 디펜던시 룩업할 수 있는 애(DL)가 뜬다
    // - 원하는 주입 시점에 주입할 수 있음

    // proxy 설정했다면
    private final MyLogger myLogger;

    public void logic(String id) {
        // MyLogger myLogger = myLoggerProvider.getObject();  //
        myLogger.log("service id: " + id);
    }
}
