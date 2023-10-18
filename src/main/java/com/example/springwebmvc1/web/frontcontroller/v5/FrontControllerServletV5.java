package com.example.springwebmvc1.web.frontcontroller.v5;

import com.example.springwebmvc1.web.frontcontroller.ModelView;
import com.example.springwebmvc1.web.frontcontroller.MyView;
import com.example.springwebmvc1.web.frontcontroller.v3.ControllerV3;
import com.example.springwebmvc1.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.springwebmvc1.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.springwebmvc1.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.springwebmvc1.web.frontcontroller.v5.adaptor.ControllerV3HandlerAdaptor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String , Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdaptor> handlerAdaptors = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();


        initHandlerAdaptors();
    }

    private void initHandlerAdaptors() {
        handlerAdaptors.add(new ControllerV3HandlerAdaptor());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());

        handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());

        handlerMappingMap.put("/front-controller/v5/v3/members",new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdaptor adaptor = getAdaptor(handler);

        ModelView mv = adaptor.handle(request, response, handler);

        String modelName = mv.getModelName();

        MyView myView = viewResolver(modelName);
        myView.render(mv.getModel(),request,response);
    }

    private static MyView viewResolver(String modelName) {
        return new MyView("/WEB-INF/views/" + modelName + ".jsp");
    }
    private MyHandlerAdaptor getAdaptor(Object handler) {
        for (MyHandlerAdaptor a : handlerAdaptors) {
            if (a.support(handler)) {
               return a;
            }
        }
        throw new IllegalArgumentException("handler adaptor 를 찾을 수 없습니다. handler = "+ handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
}
