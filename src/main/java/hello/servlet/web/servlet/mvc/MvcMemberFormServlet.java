package hello.servlet.web.servlet.mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**이 클래스는 /servlet-mvc/members/new-form으로 접근했을때
 * /servlet-mvc/members/new-form.jsp - 뷰로 이동시키기만 하는
 * 역할을 함.
 * */
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //컨트롤러에서 뷰로 이동하기 위한 경로
        dispatcher.forward(request,response);
    }

}
