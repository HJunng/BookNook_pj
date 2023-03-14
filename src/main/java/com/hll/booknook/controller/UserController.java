package com.hll.booknook.controller;

import com.hll.booknook.KakaoService;
import com.hll.user.User;
import com.hll.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    /*네이버*/
    @RequestMapping(value ="/login/naver", method = RequestMethod.GET)
    public String index(){
        log.info("home controller");
        return "NaverLogin";
    }

    //블로그에서는 /login/oauth2/code/naver 로 함.
    @RequestMapping(value="login/naver/success", method=RequestMethod.GET)
    public String loginPOSTNaver(HttpSession session){
        log.info("callback controller");
        return "NaverCallback";
    }
    /*카카오*/
    @Autowired
    private KakaoService kakaoService;

    @RequestMapping("/")
    public String main(){
        return "main";
    }
    @RequestMapping("/login")
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
        User user = new User();
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        user.setEmail(String.valueOf(userInfo.get("email")));
        user.setUsername(String.valueOf(userInfo.get("nickname")));
        user.setSns(75); //카카오의 K:아스키넘버(75)
        this.userRepository.save(user);
        return "redirect:/";//로그인 성공시 접속될 주소 입력하기
    }

}
