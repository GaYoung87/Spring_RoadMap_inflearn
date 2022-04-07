package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 서블릿이 호출되면 service가 호출됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        // http요청이 오면, WAS가 request, response객체 만들어서 서블릿에 던져줌
        // url에서 호출하면 웹브라우저가 http요청메세지 만든 것을 서버에 던진 것
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // url에 http://localhost:8080/hello?username=kim라고 작성하면 웹화면 말고 intelliJ에 username = kim이 print됨
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // response에 값을 넣으면 웹브라우저에 응답하는 메세지에 데이터가 담겨 나감
        response.setContentType("text/plain");  // http header에 들어감
        response.setCharacterEncoding("utf-8");  // http header에 들어감 / utf-8 쓰기
        response.getWriter().write("hello " + username);// http body에 메세지 들어감

    }
}
