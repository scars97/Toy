package org.example.member;

import lombok.Builder;
import lombok.Getter;

public class MemberDto {

    @Getter
    public static class SaveDto {

        private Long id;
        private String memberId;
        private String pwd;
        private String email;

        @Builder
        public SaveDto(String memberId, String pwd, String email) {
            this.memberId = memberId;
            this.pwd = pwd;
            this.email = email;
        }
    }

    @Getter
    public static class UpdateDto {

        private String pwd;
        private String email;

        @Builder
        public UpdateDto(String pwd, String email) {
            this.pwd = pwd;
            this.email = email;
        }
    }
}
