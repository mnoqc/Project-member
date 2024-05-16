package com.mnoqc.member.service;

import com.mnoqc.member.dto.MemberDTO;
import com.mnoqc.member.entity.MemberEntity;
import com.mnoqc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 정보 저장 메서드
    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository의 save 메서드 호출 (조건: Entity 객체를 넘겨 줘야 함)
    }
}