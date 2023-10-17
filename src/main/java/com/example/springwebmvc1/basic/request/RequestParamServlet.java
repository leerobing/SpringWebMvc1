package com.example.springwebmvc1.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost8080//request-param?username=hello&age=20
 * */
@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("------전체 파라미터 조회-----");
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("------------------------");

        System.out.println();
        System.out.println("-------단일 파라미터 조회-------");
        String name = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("------------------------");

        System.out.println();
        System.out.println("-----이름이 같은 복수 파라미터 조회-------");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println(username);
        }
        System.out.println("------------------------");

        response.getWriter().write("ok");
    }
}
