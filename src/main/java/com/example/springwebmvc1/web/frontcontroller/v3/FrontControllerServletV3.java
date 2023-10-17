package com.example.springwebmvc1.web.frontcontroller.v3;

import com.example.springwebmvc1.web.frontcontroller.ModelView;
import com.example.springwebmvc1.web.frontcontroller.MyView;
import com.example.springwebmvc1.web.frontcontroller.v2.ControllerV2;
import com.example.springwebmvc1.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.springwebmvc1.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.springwebmvc1.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import com.example.springwebmvc1.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.springwebmvc1.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.springwebmvc1.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {


    private Map<String , ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());

        controllerV3Map.put("/front-controller/v3/members/save",new MemberSaveControllerV3());

        controllerV3Map.put("/front-controller/v3/members",new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontController_service");

        String requestURI = request.getRequestURI();

        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createPramMap(request);

        ModelView mv = controllerV3.process(paramMap);

        String modelName = mv.getModelName();

        MyView myView = viewResolver(modelName);
        myView.render(mv.getModel(),request,response);

    }

    private static MyView viewResolver(String modelName) {
        return new MyView("/WEB-INF/views/" + modelName + ".jsp");
    }

    private static Map<String, String> createPramMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
