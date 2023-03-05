package com.hll.booknook.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class UserController {

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String index(){
        log.info("home controller");
        return "NaverLogin";
    }

    //블로그에서는 /login/oauth2/code/naver 로 함.
    @RequestMapping(value="login/naver", method=RequestMethod.GET)
    public String loginPOSTNaver(HttpSession session){
        log.info("callback controller");
        return "callback";
    }
}
