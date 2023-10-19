package com.example.springwebmvc1.web.frontcontroller.v5.adaptor;

import com.example.springwebmvc1.web.frontcontroller.ModelView;
import com.example.springwebmvc1.web.frontcontroller.v4.ControllerV4;
import com.example.springwebmvc1.web.frontcontroller.v5.MyHandlerAdaptor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdaptor implements MyHandlerAdaptor {
    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> pramMap = createPramMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(pramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private static Map<String, String> createPramMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
