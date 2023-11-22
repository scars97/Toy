package org.example;

import lombok.*;

public class Main {
    public static void main(String[] args) {
        SaveDto2 saveMember = SaveDto2.builder()
                .memberId("test1")
                .pwd("1234")
                .email("test1@email.com")
                .build();
        System.out.println(saveMember.getMemberId());
    }
    @Getter
    @Builder
    public static class SaveDto2 {
        private String memberId;
        private String pwd;
        private String email;
    }
}