package com.example.focus.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;
    private String email;
    private String pwd;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Enumerated(EnumType.STRING)
    private FocusStatus focusStatus;

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
