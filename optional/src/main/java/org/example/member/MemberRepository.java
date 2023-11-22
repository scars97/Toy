package org.example.member;

import java.util.List;

import static org.example.member.MemberDto.*;

public interface MemberRepository {

    void save(SaveDto saveDto);
    ResponseDto findById(Long id);
    List<ResponseDto> findAll();
    void update(UpdateDto updateDto, Long id);
    void delete(Long id);
}
