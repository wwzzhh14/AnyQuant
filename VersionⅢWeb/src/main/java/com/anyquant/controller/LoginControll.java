package com.anyquant.controller;

import com.anyquant.model.User;
import com.anyquant.service.LoginService;
import com.anyquant.vo.MsgVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 16/6/8.
 */

@Controller
public class LoginControll {

    @Resource
    LoginService loginService;


    @RequestMapping("/loginin")
    public String login(String username, String password, HttpSession session){
        MsgVo msgVo = loginService.login(username,password);
        if(msgVo.isSuccess()==false){
            session.setAttribute("loginResult",msgVo.getResult());
            return "redirect:login";
        }
        session.setAttribute("loginResult",null);
        session.setAttribute("user",msgVo.getUser());
        return "home";
    }

    @RequestMapping("/registerin")
    public String register(String username, String usercode,String password, String password2, HttpSession session){
        MsgVo msgVo = loginService.register(username,usercode,password,password2);
        if(msgVo.isSuccess()==false){
           session.setAttribute("registerResult",msgVo.getResult());
            return "redirect:register";
        }
        session.setAttribute("registerResult",null);


        session.setAttribute("user",msgVo.getUser());
        return "home";
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public MsgVo register(HttpSession session){
        User u =(User) session.getAttribute("user");
        if(u==null){
            return new MsgVo(false,"fail",null);
        }
        return  new MsgVo(true,"success",u);
    }


    @RequestMapping("/loginOut")

    public String loginOut(HttpSession session){
        User u =(User) session.getAttribute("user");
        if(u==null){
            return "home";
        }
        session.setAttribute("user",null);
        return "home";
    }

}
