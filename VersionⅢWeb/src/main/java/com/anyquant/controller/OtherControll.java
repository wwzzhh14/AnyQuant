package com.anyquant.controller;

import com.anyquant.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 16/6/8.
 */
@Controller
public class OtherControll {
    @RequestMapping("/contact")
    public void contact(){}

    @RequestMapping("/about")
    public void about(){}

    @RequestMapping("/login")
    public void login(){}

    @RequestMapping("/personal")
    public String personal(HttpSession session){

        User u = (User) session.getAttribute("user");
        if(u ==null){
            return "login";
        }

        return "personal";
    }

    @RequestMapping("/plate")
    public void plate(){}

    @RequestMapping("/recommend")
    public void recommend(){}

    @RequestMapping("/register")
    public void register(){}

    @RequestMapping("/stocklist")
    public void stocklist(){}

    @RequestMapping("/index")
    public void index(){}

    @RequestMapping("/home")
    public void home(){}

    @RequestMapping("/download")
    public void download(){}


    @RequestMapping("/contactresult")
    public void contactresult(){}
}
