package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
/**
 * API 단순 데이터 만들어 보내기
 * */


@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        //메세지 내용을 바이트 코드로 받는 메소드
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //위에서 받은 바이트 코드를 스트링으로 변환시켜주는 StreamUtils.copyToString()
        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("OK");
    }
}
