package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@Slf4j
public class RequestHeaderController {

    /** MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
    HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
    keyA=value1&keyA=value2
     */

    @RequestMapping("/headers")
    public String header(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpMethod httpMethod,
                         Locale locale,
                         @RequestHeader MultiValueMap<String, String> headerMap,
                         @RequestHeader("host") String host,
                         @CookieValue(value = "myCookie", required = false) String cookie
                         ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);//언어정보
        log.info("headerMap={}", headerMap);//모든 헤더정보
        log.info("header host={}", host);//특정 해더 정보
        log.info("myCookie={}", cookie);//밸류는 이름붙이기, 리콰이어는 밸류값 추출여부
        return "ok";
    }
}
