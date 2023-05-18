package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controller어노테이션의 경우 뷰모델을 반환해야하는데
//아래 @RestController를 사용하면 리턴값을 그대로 반환해서 보여줌
//slf4j붙이면 아래 주석처럼 로그 객체 안만들어도됨
@Slf4j
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "spring";
        System.out.println("name = " + name);

        log.trace("trace lo ={}", name);
        log.debug("debug lo ={}", name);
        log.info("info lo ={}", name);
        log.warn("warn lo ={}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
