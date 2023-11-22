package org.example.service;

import org.example.member.MemberRepository;
import org.example.member.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

import static org.example.member.MemberDto.*;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private static final String DATA_CHECK_ERROR = "데이터를 확인해주세요.";
    private static final String DATA_NOT_FOUND = "저장된 회원이 없습니다.";

    public Long saveMember(SaveDto saveDto) {
        Optional<SaveDto> saveMember = Optional.ofNullable(saveDto);
        return memberRepository.save(saveMember.orElseThrow(() -> new IllegalArgumentException(DATA_CHECK_ERROR)));
    }

    public ResponseDto findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(DATA_CHECK_ERROR);
        }
        return memberRepository.findById(id);
    }

    public List<ResponseDto> findAll() {
        List<ResponseDto> allMember = memberRepository.findAll();
        if (allMember.isEmpty()) {
            throw new IllegalArgumentException(DATA_NOT_FOUND);
        }

        return allMember;
    }

    public void update(UpdateDto updateDto, Long id) {
        if (updateDto == null || id == null) {
            throw new IllegalArgumentException(DATA_CHECK_ERROR);
        }
        memberRepository.update(updateDto, id);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(DATA_CHECK_ERROR);
        }
        memberRepository.delete(id);
    }
}
