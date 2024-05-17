package com.mnoqc.member.dto;

import com.mnoqc.member.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// 회원 정보를 담는 DTO (Data Transfer Object) 클래스
public class MemberDTO {
    private Long id; // 회원 고유 식별자
    private String memberEmail; // 회원 이메일
    private String memberPassword; // 회원 비밀번호
    private String memberName; // 회원 이름

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        return memberDTO;
    }
}
