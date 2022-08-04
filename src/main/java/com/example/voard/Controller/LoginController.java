package com.example.voard.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.voard.Dto.MemberDto;
import com.example.voard.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController { 
    
    private final MemberService memberService;

    @GetMapping("/join")
    public ModelAndView join(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("join");

        return mv;
    }

    @PostMapping("/signUp")
    public RedirectView signUp(MemberDto memberDto){
        memberService.joinUser(memberDto);
        
        return new RedirectView("/board");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");

        return mv;
    }

}
