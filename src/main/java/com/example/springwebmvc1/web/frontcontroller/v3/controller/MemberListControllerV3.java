package com.example.springwebmvc1.web.frontcontroller.v3.controller;

import com.example.springwebmvc1.domain.member.Member;
import com.example.springwebmvc1.domain.member.MemberRepository;
import com.example.springwebmvc1.web.frontcontroller.ModelView;
import com.example.springwebmvc1.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMep) {


        List<Member> memberList = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members",memberList);

        return mv;
    }
}
