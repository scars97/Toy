package org.example.service;

import org.example.member.MemberRepository;
import org.example.member.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

import static org.example.member.MemberDto.*;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public void saveMember(SaveDto saveDto) {
        Optional<SaveDto> saveMember = Optional.ofNullable(saveDto);
        memberRepository.save(saveMember.orElseThrow(() -> new IllegalArgumentException("입력값 확인 필요")));
    }

    public ResponseDto findById(Long id) {
        Optional<ResponseDto> findMember = Optional.ofNullable(memberRepository.findById(id));
        return findMember.orElseThrow(() -> new IllegalArgumentException("저장된 회원 정보 없음"));
    }

    public List<ResponseDto> findAll() {
        return memberRepository.findAll();
    }
}
