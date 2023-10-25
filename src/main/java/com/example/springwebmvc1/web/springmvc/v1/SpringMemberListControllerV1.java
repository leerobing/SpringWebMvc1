package com.example.springwebmvc1.web.springmvc.v1;

import com.example.springwebmvc1.domain.member.Member;
import com.example.springwebmvc1.domain.member.MemberRepository;
import com.example.springwebmvc1.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {

        List<Member> memberList = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",memberList);

        return mv;
    }
}
