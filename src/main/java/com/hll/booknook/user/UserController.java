package com.hll.booknook.user;

import com.hll.booknook.KakaoService;
import com.hll.booknook.NaverService;
import com.hll.booknook.user.User;
import com.hll.booknook.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
    @Autowired
    private NaverService naverService;
    private final UserRepository userRepository;

    /*메인 페이지*/
    @RequestMapping("/")
    public String main(){
        return "main";
    }

    /*네이버 로그인*/
    @RequestMapping(value="login/naver_auth", method=RequestMethod.GET)
    public String naver_login(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();

        User user = new User();
        String access_Token = naverService.getAccessToken(code);
        HashMap<String, Object> userInfo = naverService.getUserInfo(access_Token);
        User u = this.userRepository.findBySnsAndEmail(78, String.valueOf(userInfo.get("email")));
        //등록된 사용자 정보가 없으면 regist user
        if (u == null) {
            user.setEmail(String.valueOf(userInfo.get("email")));
            user.setUsername(String.valueOf(userInfo.get("name")));
            user.setSns(78); //카카오의 K:아스키넘버(75) N:아스키넘버(78)
            this.userRepository.save(user);
        }
        session.setAttribute("access_token", access_Token);
        session.setAttribute("name", u.getUsername());
        session.setMaxInactiveInterval(20*60);
        return "redirect:/login";
    }

    /*카카오 로그인*/
    @Autowired
    private KakaoService kakaoService;

    @RequestMapping(value="/login/auth", method=RequestMethod.GET)
    public String kakao_login(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception{
        //로그인 세션
        HttpSession session = request.getSession();

        User user = new User();
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        User u = this.userRepository.findBySnsAndEmail(75, String.valueOf(userInfo.get("email")));
        //등록된 사용자 정보가 없으면 regist user
        if (u == null) {
            user.setEmail(String.valueOf(userInfo.get("email")));
            user.setUsername(String.valueOf(userInfo.get("name")));
            user.setSns(75); //카카오의 K:아스키넘버(75)
            this.userRepository.save(user);
        }
        //로그인 세션 생성하기
        session.setAttribute("access_token", access_Token);
        session.setAttribute("name", u.getUsername());
        session.setMaxInactiveInterval(20*60);
        return "redirect:/login";
    }

    //카카오 로그아웃: 세션 삭제하기
    //네이버 로그아웃=세션 삭제 : 네이버 로그아웃은 api 지원 안함
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String home(Model model, @RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception {
        //로그인 세션 받아오기
        HttpSession getsession = request.getSession();
        String login_name = (String) getsession.getAttribute("name");
        if (login_name == null) {
            return "main";
        } else {
            model.addAttribute("name", login_name);
            return "main";
        }
    }

}
