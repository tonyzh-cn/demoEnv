package com.example.demo.springDemo.bean.scope.web;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private User user;

    @RequestMapping("index.html")
    public String index(Model model){
        User u =applicationContext.getBean("user",User.class);
        System.out.println(user == u);
//        User u2 = applicationContext.getBean("scopedTarget.user",User.class);
        model.addAttribute("userObject",user);
        return "index";
    }

}
