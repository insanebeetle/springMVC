package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requsetBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJSonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    //Json을 객체로 만들기, 변환하기 위한 오브젝트매퍼 객체 생성
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("message = " + message);
        //이대로 출력하면 제이슨 형식 그대로 보여줌
        HelloData helloData = objectMapper.readValue(message, HelloData.class);
        //클래스로 만들어 놓은 hellodata에 message데이터를 readvalue로 읽어들여서 집어넣기
        System.out.println("helloData.userqname = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("OK");
    }
}
