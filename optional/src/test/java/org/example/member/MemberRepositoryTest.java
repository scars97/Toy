package org.example.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.example.member.MemberDto.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void save() {
        //given
        Long createId = createMember();

        //when
        ResponseDto findMember = memberRepository.findById(createId);

        //then
        assertEquals(findMember.getId(), createId);
    }

    @Test
    void findAll() {
        //given
        SaveDto saveMember1 = SaveDto.builder()
                .memberId("test1")
                .pwd("1234")
                .email("test1@email.com")
                .build();
        SaveDto saveMember2 = SaveDto.builder()
                .memberId("test2")
                .pwd("1234")
                .email("test2@email.com")
                .build();
        memberRepository.save(saveMember1);
        memberRepository.save(saveMember2);

        //when
        List<ResponseDto> allMember = memberRepository.findAll();

        //then
        System.out.println(allMember);
    }

    @Test
    void update() {
        //given
        Long createId = createMember();
        UpdateDto updateMember = UpdateDto.builder()
                .pwd("1345")
                .email("update@email.com")
                .build();

        //when
        memberRepository.update(updateMember, createId);
        ResponseDto findMember = memberRepository.findById(createId);

        //then
        assertEquals(findMember.getEmail(), updateMember.getEmail());
    }

    @Test
    void delete() {
        //given
        Long createMemberId = createMember();
        ResponseDto beforeMember = memberRepository.findById(createMemberId);

        //when
        memberRepository.delete(createMemberId);

        //then
        assertThrows(IllegalArgumentException.class, () -> memberRepository.findById(createMemberId));
    }

    private Long createMember() {
        SaveDto saveMember = SaveDto.builder()
                .memberId("test1")
                .pwd("1234")
                .email("test1@email.com")
                .build();
        return memberRepository.save(saveMember);
    }
}