package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {


    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age ={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    //원래 클래스에서 RestController를 사용해 string타입을 반환하게 하는걸 대신해줌
    @RequestMapping("/request-param-v2")
    public String requestParamV2 (
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username ={}, age ={}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3 (
            @RequestParam String username,
            @RequestParam int age){

        log.info("username ={}, age ={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4 (String username, int age){

        log.info("username ={}, age ={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    //필수 항목 설정은 required로
    public String requestParamRequired (
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age){
    /*
    * 참고 int형에는 null이 들어갈수 없다 따라서 위 코드의 경우 age값을 안줄경우 에러가 발생함
    * 따라서 int형이 아니라 Integer형으로 넣어야 함
    * + String의 경우는 null이 아니라 ""이라는 값이 들어간다 이는 다르게 처리해야함
    * */

        log.info("username ={}, age ={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault (
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false,defaultValue = "-1") int age){
        //애초에 디폴트를 설정하면 required가 필요가 없음
        log.info("username ={}, age ={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap (@RequestParam Map<String, Object> paramMap){
        log.info("username ={}, age ={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttribute(@RequestParam String username, @RequestParam int age) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//        log.info("username ={}, age ={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData = {}", helloData);
//        //hellodata에 있는 lombok(@Data)수식에는 그냥 문자열로 찍어주는 toString메소드가
//        //포함되어 있어서 그대로 찍어낼수 있음
//        return "ok";
//    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    //위 주석의 간편판.. ㅅㅂ 존나사기.
    //객체를 파라메터 수준에서 생성해서 파라메타로 넘겨줌
    //위에서 처럼 각각의 파라메타를 같은 변수명에 롬복을 이용해 넣어줌
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username ={}, age ={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username ={}, age ={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }
}
