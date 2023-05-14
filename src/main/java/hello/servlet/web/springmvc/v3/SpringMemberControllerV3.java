package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //기본적으로 스프링은 어떤 요청으로 들어오는지 신경쓰지않음(겟,포스트)
    //method =RequestMethod를 설정해서 받아들이는 방식을 정할 수 있음
    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    //라고 하자마자 무친놈들이 겟,포스트에 따른 메핑을 만들어놓음
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String save(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "members";
    }


    //httpsevletmapping 으로 모든 요청값을 받지 않고 아래처럼
    // RequestParam을 사용하면 원하는 쿼리만 받을 수 있다
    // + model도 전달값있으니 그대로사용가능
    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String members(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}
