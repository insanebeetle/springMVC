package hello.springmvc.basic.request;


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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requstBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //위 두줄이 스트림으로 바디를 스트링으로 뽑아내는 코드
        log.info("messageBody ={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requstBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //위 두줄이 스트림으로 바디를 스트링으로 뽑아내는 코드
        log.info("messageBody ={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requstBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody ={}", messageBody);
        return new HttpEntity<>("ok");
        //위처럼 사용시 http바디를 그대로 받아서 쓰는 형태가 된다
        //스트림을 따로 안받고 걍 쓸수 있어서 존나 편함
        //이를 가능하게 하는게 Http 메세지 컨버터라는 기능덕분임
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requstBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody ={}", messageBody);
        return "ok";
        //파라메타상에서 바디정보를 바로 받아서 사용 - 이 방식이 실무에서 가장많이 쓰임
    }
}
