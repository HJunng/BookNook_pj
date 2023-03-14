package com.hll.booknook.controller;

import com.hll.booknook.KakaoService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@Slf4j
public class UserController {

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
    @Controller
    public class KakaoController {
        @Autowired
        private KakaoService kakaoService;

        @RequestMapping("/")
        public String main(){
            return "main";
        }
        @RequestMapping("/login")
        public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
            System.out.println("#########" + code);
            String access_Token = kakaoService.getAccessToken(code);
            HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
            System.out.println("###userInfo#### : " + userInfo.get("email"));
            System.out.println("###nickname#### : " + userInfo.get("nickname"));
            System.out.println("###access_Token#### : " + access_Token);
            return "redirect:/";//로그인 성공시 접속될 주소 입력하기
        }
    }

}
