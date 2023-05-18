package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    /*
     * /response-view-v1 URL로 들어오면
     * <-response/hello경로에 있는 뷰를 반환함
     * 이때 addObject로 view에 쓰일 데이터를 넘겨줌
     * */
    @RequestMapping("/response-view-v1")
    //modelandView 를 반환하면 뷰를 해당 뷰를 반환해줌
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "Hello!");
        return mav;
    }
    /* 위에 모델만들경우 addObject사용하는건(데이터추가) 공식처럼 외워놓기
    * 위와 같은 코드
    * @Controller 에서 String을 반환하면 그자체로 경로가 될 수 있음!!!!
    * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    * */
    @RequestMapping("/response-view-v2")
    //modelandView 를 반환하면 뷰를 해당 뷰를 반환해줌
    public String responseViewV2(Model model){
                model.addAttribute("data", "Hello!");
        return "response/hello";
    }
    //모델을 파라메터에서 만들경우는 addAttribute를 사용!
    /*
    * 경로가 그자체로 uri가 되는 경우
    * void반환은 @Controller사용 & httpservletresponse등을 사용하지 않을때만 사용가능    *
    * 근데 url자체가 이대로 출력되기 때문에 애매하고
    * url이랑 뷰이름을 같게 함으로써 각각의 역할을 하나의 이름이 가지게됨
    * */
    @RequestMapping("response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "Hello!");
    }
}
