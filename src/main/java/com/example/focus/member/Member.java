package com.example.focus.member;

import jakarta.persistence.*;
import lombok.*;

import static com.example.focus.member.FocusStatus.NONE;
import static com.example.focus.member.LoginType.LOCAL;
import static com.example.focus.member.UserType.USER;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username; // 사용자 이름

    @Column(name = "email", unique = true)
    private String email; // 로그인 이메일

    private String pwd;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String nickname; // 사용자 지정 닉네임

    @Enumerated(EnumType.STRING)
    private UserType userType; // 관리자, 일반 계정 구분

    @Enumerated(EnumType.STRING)
    private FocusStatus focusStatus; // 집중 상태

    @Builder
    public Member(String username, String email, String pwd, LoginType loginType, String nickname, UserType userType, FocusStatus focusStatus) {
        this.username = username;
        this.email = email;
        this.pwd = pwd;
        this.loginType = LOCAL;
        this.nickname = nickname;
        this.userType = USER;
        this.focusStatus = NONE;
    }

    public MemberDto toDto() {
        return MemberDto.builder()
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
