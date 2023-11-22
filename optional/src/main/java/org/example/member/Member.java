package org.example.member;

import lombok.*;

@Getter
@Builder
public class Member {

    private Long id;
    private String memberId;
    private String pwd;
    private String email;
}
