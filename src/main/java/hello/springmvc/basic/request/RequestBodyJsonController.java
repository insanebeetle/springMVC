package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("MessageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("MessageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /*
    * 아래 코드에서 RequestHeaderController에서 처럼 @RequsetBody 애노테이션을
    * 생략하면 안된다!! 이유는
    * 생략할 경우 @RequsetBody가 아닌 @ModelAttribute가 적용되기 때문
    * */

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("MessageBody = {}", helloData);
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //HttpEntity로 사용해서 객체를 생성할 경우
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) throws IOException {
        HelloData helloData = data.getBody();
        log.info("MessageBody = {}", helloData);
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //요청파라메타를 응답값으로 반환하는것도 가능
    //제이슨이 객체가 되었다가 다시 제이슨이 되어 나가는 흐름
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        log.info("MessageBody = {}", data);
        log.info("username = {}, age={}", data.getUsername(), data.getAge());
        return data;
    }
}
