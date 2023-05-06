package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("helloServlet");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        //쿼리 파라메터 읽어오는 법

        response.setContentType("text/plane");
        response.setCharacterEncoding("utf-8");//브라우저에게 응답시 반환할 정보(html등)의 규격정하기
        response.getWriter().write("hello"+ username);//브라우저 상의 구체적인 화면 리턴

    }
}
