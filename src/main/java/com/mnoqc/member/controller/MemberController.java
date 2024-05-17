package com.mnoqc.member.controller;

import com.mnoqc.member.dto.MemberDTO;
import com.mnoqc.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/member/")
// HTTP GET 요청에 매핑되는 메소드를 선언. "/member/" 경로에 매핑.
    public String findAll(Model model) {
        // 서비스를 통해 모든 회원을 조회.
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 조회된 회원 목록을 HTML 페이지로 전달하기 위해 모델에 추가.
        model.addAttribute("memberList", memberDTOList);
        // "list"라는 이름의 HTML 템플릿 반환.
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateGorm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

}
