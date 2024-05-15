package com.mnoqc.member.service;

import com.mnoqc.member.dto.MemberDTO;
import com.mnoqc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 정보 저장 메서드
    public void save(MemberDTO memberDTO) {


    }
}