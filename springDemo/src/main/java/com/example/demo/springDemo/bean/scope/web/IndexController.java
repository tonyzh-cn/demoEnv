package com.example.demo.springDemo.bean.scope.web;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private User user;

    @RequestMapping("index.html")
    public String index(HttpServletRequest request, HttpServletResponse response, User user,User user2){
        User u =applicationContext.getBean("user",User.class);
        System.out.println(user == u);
//        User u2 = applicationContext.getBean("scopedTarget.user",User.class);
        request.setAttribute("userObject",user);
        return "index";
    }

}
