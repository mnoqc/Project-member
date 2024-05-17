package com.mnoqc.member.service;

import com.mnoqc.member.dto.MemberDTO;
import com.mnoqc.member.entity.MemberEntity;
import com.mnoqc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // MemberDTO 객체(회원정보)를 데이터베이스에 저장하는 메소드
    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        // 2. repository의 save 메서드 호출하여 MemberEntity 저장
        memberRepository.save(memberEntity);
        // repository의 save 메서드 호출 (조건: Entity 객체를 넘겨 줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치(로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        // 모든 회원 엔티티를 데이터베이스에서 조회하여 리스트로 가져옵니다.
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        // 회원 DTO 객체를 저장할 리스트를 생성합니다.
        List<MemberDTO> memberDTOList = new ArrayList<>();
        // 조회된 각 회원 엔티티를 반복하면서 DTO로 변환하고 리스트에 추가합니다.
        for (MemberEntity memberEntity : memberEntityList) {
            // 회원 엔티티를 회원 DTO로 변환하여 리스트에 추가합니다.
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            // 위 주석 처리된 두 줄과 동일한 기능을 수행하는 코드입니다.
            // MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            // memberDTOList.add(memberDTO);
        }
        // DTO로 변환된 회원 목록을 반환합니다.
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }

    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}