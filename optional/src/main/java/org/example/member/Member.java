package org.example.member;

import com.sun.istack.internal.NotNull;
import lombok.*;

@Getter
@Builder
class Member {

    private Long id;
    @NotNull
    private String memberId;
    @NotNull
    private String pwd;
    private String email;
}
