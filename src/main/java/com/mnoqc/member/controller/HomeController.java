package com.mnoqc.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 기본 페이지 요청 메서드
    @GetMapping("/") // 사용자가 localhost:8081/로 접속하면 이 메서드가 실행됨
    public String index() {
        return "index"; // templates 폴더의 index.html을 렌더링하여 사용자에게 보여줌 (기본 화면)
    }
}
