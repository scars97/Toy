package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

    private String id;
    private String pwd;
    private String email;

    @Builder
    public Member(String id, String pwd, String email) {
        this.id = id;
        this.pwd = pwd;
        this.email = email;
    }
}
