package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
* 파라미터 전송 기능
* */

@WebServlet(name = "requsetParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("all param check start");
        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("all param check end");
        System.out.println();

        System.out.println("1 param check");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println(username + " : "+ age);
        System.out.println();

        System.out.println("same name parameter check");
        String[] usernames = request.getParameterValues("username");
        for (String name: usernames
             ) {
            System.out.println("name = " + name);
        }
        response.getWriter().write("OK");
    }
}
