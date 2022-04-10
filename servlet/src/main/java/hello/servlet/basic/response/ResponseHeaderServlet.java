package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답코드 세팅[status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Context-Type", "text/plain");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  // 캐시 완전히 부여
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
}
