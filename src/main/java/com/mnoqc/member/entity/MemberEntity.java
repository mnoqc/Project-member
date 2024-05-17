package com.mnoqc.member.entity;


import com.mnoqc.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) // unique 제약 조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    // MemberDTO를 MemberEntity로 변환하는 정적 메소드
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        // 새로운 MemberEntity 객체 생성
        MemberEntity memberEntity = new MemberEntity();

        // DTO에서 멤버 이메일, 패스워드, 이름을 추출하여 MemberEntity에 설정
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());

        // 변환된 MemberEntity 객체 반환
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        // 새로운 MemberEntity 객체 생성
        MemberEntity memberEntity = new MemberEntity();
        // DTO에서 가져온 ID 값을 MemberEntity의 ID로 설정
        memberEntity.setId(memberDTO.getId());
        // DTO에서 멤버 이메일, 패스워드, 이름을 추출하여 MemberEntity에 설정
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());

        // 변환된 MemberEntity 객체 반환
        return memberEntity;
    }

}
