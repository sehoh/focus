package com.example.focus.member;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String username;
    private String email;
    private String pwd;
    private LoginType loginType;
    private String nickname;
    private UserType userType;
    private FocusStatus focusStatus;


    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .pwd(this.pwd)
                .loginType(this.loginType)
                .nickname(this.nickname)
                .userType(this.userType)
                .focusStatus(this.focusStatus)
                .build();
    }
}
