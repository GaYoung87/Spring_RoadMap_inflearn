package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("paramName = " + request.getParameter(paramName)));
        // getParameter를 뽑았기 때문에 parameter는 한개, parameter의 값이 여러개

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        /**
         * 이때, 하나의 파라미터에 여러개 값을 넘길 수 있음
         * http://localhost:8080/request-param?username=hello&username=hello2 가능!
         * [단일 파라미터 조회]의 경우 먼저 나와있는 파라미터 값으로 내보냄
         */

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        // request.getParameter() 는 하나의 파라미터 이름에 대해서 단 하나의 값만 있을 때 사용해야 한다.
        // 중복일 때 request.getParameter() 를 사용하면 request.getParameterValues()의 첫 번째 값을 반환한다.
        String[] usernames = request.getParameterValues("username");  // 배열로 나옴
        for (String name : usernames) {
            System.out.println("name = " + name);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
        System.out.println();

        response.getWriter().write("ok");

    }
}
