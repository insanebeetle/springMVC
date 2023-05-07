package hello.servlet.basic.response;

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

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper =new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        HelloData helloData =new HelloData();
        helloData.setUsername("YU");
        helloData.setAge(20);

        //위 hellodata객체를 json형태로 바꿔줘야함
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

        /*내가 따로 추가한 부분
        * 파람스로 받은 제이슨데이터를 리스폰스로 되돌려 보여주기*/
//        ServletInputStream inputStream = request.getInputStream();
//        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//        HelloData helloData1 = objectMapper.readValue(message, HelloData.class);
//        response.getWriter().write(helloData1.getUsername());
//        response.getWriter().write(helloData1.getAge());
    }
}
