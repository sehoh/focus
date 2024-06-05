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
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String email;
    private String pwd;
    private LoginType loginType;
    private String nickname;
    private UserType userType;
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
