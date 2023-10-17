package com.example.springwebmvc1.web.frontcontroller.v3.controller;

import com.example.springwebmvc1.domain.member.Member;
import com.example.springwebmvc1.domain.member.MemberRepository;
import com.example.springwebmvc1.web.frontcontroller.ModelView;
import com.example.springwebmvc1.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMep) {
        String username = paramMep.get("username");
        int age = Integer.parseInt(paramMep.get("age"));

        Member member = new Member(username,age);

        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;
    }
}
