package org.example.member;

import lombok.*;

public class MemberDto {

    @Getter
    @Builder
    public static class SaveDto {
        private String memberId;
        private String pwd;
        private String email;
    }

    @Getter
    @Builder
    public static class UpdateDto {
        private String pwd;
        private String email;
    }

    @Getter
    @ToString
    public static class ResponseDto {
        private Long id;
        private String memberId;
        private String email;

        public ResponseDto(Member member) {
            this.id = member.getId();
            this.memberId = member.getMemberId();
            this.email = member.getEmail();
        }
    }
}
