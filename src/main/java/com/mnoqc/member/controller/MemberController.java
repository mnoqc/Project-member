package com.mnoqc.member.controller;

import com.mnoqc.member.dto.MemberDTO;
import com.mnoqc.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
    public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 요청 메서드
    @GetMapping("/member/save") // 사용자가 localhost:8081/member/save로 접속하면 이 메서드가 실행됨
    public String saveForm() {
        return "save";
    }  // templates 폴더의 save.html을 렌더링하여 사용자에게 보여줌 (기본 화면)

    // 회원가입 처리 메서드
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login"; // 회원가입 후 로그인 화면으로 이동
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    // 로그인 처리 메서드
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }
}