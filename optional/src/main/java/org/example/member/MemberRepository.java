package org.example.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> storage = new HashMap<>();
    private static long sequence = 0L;

    public void save(MemberDto.SaveDto member) {
        Member saveMember = Member.builder()
                .id(sequence++)
                .memberId(member.getMemberId())
                .pwd(member.getPwd())
                .email(member.getEmail())
                .build();
        storage.put(saveMember.getId(), saveMember);
    }

    public Member findById(Long id) {
        return storage.get(id);
    }
}
