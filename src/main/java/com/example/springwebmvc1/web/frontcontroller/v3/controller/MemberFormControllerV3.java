package com.example.springwebmvc1.web.frontcontroller.v3.controller;

import com.example.springwebmvc1.web.frontcontroller.ModelView;
import com.example.springwebmvc1.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {


    @Override
    public ModelView process(Map<String, String> paramMep) {
        return new ModelView("new-form");
    }
}
