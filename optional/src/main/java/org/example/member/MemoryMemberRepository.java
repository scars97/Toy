package org.example.member;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.member.MemberDto.*;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> storage = new HashMap<>();
    private static long sequence = 0L;

    /// 회원 저장
    public Long save(SaveDto member) {
        Member saveMember = Member.builder()
                .id(sequence++)
                .memberId(member.getMemberId())
                .pwd(member.getPwd())
                .email(member.getEmail())
                .build();
        storage.put(saveMember.getId(), saveMember);
        return saveMember.getId();
    }

    // 회원 조회
    public ResponseDto findById(Long id) {
        Optional<Member> member = Optional.ofNullable(storage.get(id));
        return new ResponseDto(member.orElseThrow(() -> new IllegalArgumentException("저장된 회원이 없습니다.")));
    }

    // 전체 회원 조회
    public List<ResponseDto> findAll() {
        return storage.values().stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }

    //회원 업데이트
    public void update(UpdateDto updateDto, Long id) {
        Member findMember = storage.get(id);
        Member updateMember = Member.builder()
                .id(findMember.getId())
                .memberId(findMember.getMemberId())
                .pwd(updateDto.getPwd())
                .email(updateDto.getEmail())
                .build();
        storage.put(updateMember.getId(), updateMember);
    }

    // 회원 삭제
    public void delete(Long id) {
        storage.remove(id);
    }
}
